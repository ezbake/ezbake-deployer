/*   Copyright (C) 2013-2014 Computer Sciences Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package ezbake.deployer.publishers;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import ezbake.base.thrift.EzSecurityToken;
import ezbake.configuration.constants.EzBakePropertyConstants;
import ezbake.deployer.AccumuloEzDeployerStore;
import ezbake.deployer.publishers.database.DatabaseSetup;
import ezbake.deployer.utilities.ArtifactHelpers;
import ezbake.deployer.utilities.CertDataEntry;
import ezbake.deployer.utilities.SSLCertsService;
import ezbake.ezdiscovery.ServiceDiscovery;
import ezbake.ezpurge.ServicePurgeClient;
import ezbake.services.deploy.thrift.ArtifactType;
import ezbake.services.deploy.thrift.DeploymentArtifact;
import ezbake.services.deploy.thrift.DeploymentException;
import ezbake.services.deploy.thrift.DeploymentMetadata;
import ezbakehelpers.ezconfigurationhelpers.zookeeper.ZookeeperConfigurationHelper;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Nullable;

import static com.google.common.base.Strings.nullToEmpty;
import static ezbake.deployer.utilities.ArtifactHelpers.*;
import static ezbake.deployer.utilities.Utilities.*;


/**
 * This object will publish an given application to the proper PaaS.
 */
public class EzDeployPublisher implements EzPublisher {
    private static final Logger log = LoggerFactory.getLogger(EzDeployPublisher.class);
    public static final String CONFIG_APPLICATION_PROPERTIES = CONFIG_DIRECTORY + "/application.properties";


    private final EzPublisherMapping publishers;

    private final SSLCertsService certsService;
    private final ServiceDiscovery serviceDiscovery;
    private final String zookeeperConnectionString;
    private final Set<DatabaseSetup> possibleSetups;
    private final Properties configuration;

    /**
     * Creates a publisher with the given implementations.  This is to allow tests to specify mock objects
     *
     * @param publishers   - Mappings of ArtifactType to publishers.
     * @param certsService - The service to which the certs come from
     * @param configuration - The configuration properties used to derive the zookeeper string to set items as purgeable
     * @param serviceDiscoveryClient - The service discovery service to which to register the published apps with
     * @param possibleSetups - Possible database publishers
     */
    @Inject
    public EzDeployPublisher(EzPublisherMapping publishers, SSLCertsService certsService, Properties configuration,
                             @Nullable ServiceDiscovery serviceDiscoveryClient, Set<DatabaseSetup> possibleSetups) {
        this.publishers = publishers;
        this.certsService = certsService;
        this.serviceDiscovery = serviceDiscoveryClient;
        this.zookeeperConnectionString = new ZookeeperConfigurationHelper(configuration).getZookeeperConnectionString();
        //These two are for setting up the dataset publisher for things that have a database but aren't a dataset
        this.configuration = configuration;
        this.possibleSetups = possibleSetups;
    }

    /**
     * This will publish the artifact to the correct PaaS, based on the metadata contained in the param.
     * <p/>
     * SSLCerts will be injected into the application tar ball at this time.
     *
     * @param artifact - The artifact to deploy
     * @throws DeploymentException - on any exception
     */
    public void publish(DeploymentArtifact artifact, EzSecurityToken callerToken) throws DeploymentException {
        fillInExtraFiles(artifact);
        registerSecurityIDWithServiceDiscovery(artifact.getMetadata());
        EzPublisher publisher = publishers.get(getType(artifact));
        //If something is a dataset, the publisher should already be an EzDataSetPublisher, but we also
        //allow apps that aren't datasets to get a database, so we need to use the DataSet publisher there too
        if (ArtifactHelpers.getType(artifact) != ArtifactType.DataSet &&
                artifact.getMetadata().getManifest().isSetDatabaseInfo()) {
            EzDataSetPublisher dataSetPublisher = new EzDataSetPublisher(publisher, possibleSetups, configuration);
            dataSetPublisher.publish(artifact, callerToken);
        } else {
            publisher.publish(artifact, callerToken);
        }
        //if artifact is purgable add it to the zookeeper purgeable list
        if(artifact.getMetadata().getManifest().getArtifactInfo().isPurgeable()){
            setPurgeable(artifact);
        }
    }

    public void unpublish(DeploymentArtifact artifact, EzSecurityToken callerToken) throws DeploymentException {
        EzPublisher publisher = publishers.get(getType(artifact));
        publisher.unpublish(artifact, callerToken);
        //if artifact is purgable remove it from the zookeeper purgeable list
        if(artifact.getMetadata().getManifest().getArtifactInfo().isPurgeable()){
            unsetPurgeable(artifact);
        }
    }


    /**
     * This will retrieve the SSLCerts for the application, and inject it into the tar ball in the correct spot
     * Also it will add to the config directory an application.properties.
     *
     * @param artifact - The application artifact to inject the SSL Certs into
     * @throws DeploymentException - On any errors generating/reading the application tar ball, or retrieving the SSL Certs
     */
    private void fillInExtraFiles(DeploymentArtifact artifact) throws DeploymentException {
        List<CertDataEntry> injectFiles = certsService.get(getServiceId(artifact), getSecurityId(artifact));

        injectFiles.addAll(createApplicationProperties(artifact));
        ArtifactHelpers.addFilesToArtifact(artifact, injectFiles);
    }

    private Collection<CertDataEntry> createApplicationProperties(DeploymentArtifact artifact) throws DeploymentException {

        Map<String, String> valuesMap = Maps.newHashMap();
        String appId = getAppId(artifact);
        if (appId != null)
            valuesMap.put(EzBakePropertyConstants.EZBAKE_APPLICATION_NAME, appId);
        valuesMap.put(EzBakePropertyConstants.EZBAKE_SERVICE_NAME, getServiceId(artifact));
        valuesMap.put(EzBakePropertyConstants.EZBAKE_APPLICATION_VERSION, artifact.getMetadata().getVersion());
        String securityId = getSecurityId(artifact);
        checkCondition("securityId is required to be set", securityId != null);
        valuesMap.put(EzBakePropertyConstants.EZBAKE_SECURITY_ID, securityId);
        valuesMap.put(EzBakePropertyConstants.EZBAKE_CERTIFICATES_DIRECTORY, String.format("%s/%s", SSL_CONFIG_DIRECTORY, securityId));
        String properties = Joiner.on('\n').withKeyValueSeparator("=").join(valuesMap);
        return Lists.newArrayList(new CertDataEntry(new TarArchiveEntry(CONFIG_APPLICATION_PROPERTIES), properties.getBytes()));
    }

    private void checkCondition(String description, boolean exp) throws DeploymentException {
        if (!exp) {
            DeploymentException e = new DeploymentException(description);
            log.error(description, e);
            throw e;
        }
    }


    private void registerSecurityIDWithServiceDiscovery(DeploymentMetadata artifact) throws DeploymentException {
        if ( serviceDiscovery == null ) {
            log.warn("Service Discovery is not set, so can not register security id.  Check zookeeper config");
            return; // if not configured to use, skip this step
        }
        Preconditions.checkState(!Strings.isNullOrEmpty(getSecurityId(artifact)),
                "A security ID MUST be passed in for the application/service");
        try {
            if (Strings.isNullOrEmpty(getAppId(artifact)) || getAppId(artifact).equals("common_services") ) {
                log.info("Registering common service " +getServiceId(artifact) + " secId=" + getSecurityId(artifact));
                serviceDiscovery.setSecurityIdForCommonService(getServiceId(artifact), getSecurityId(artifact));
            } else {
                log.info("Registering " + getAppId(artifact) + ":" + getServiceId(artifact) + " secId=" + getSecurityId(artifact));
                serviceDiscovery.setSecurityIdForApplication(getAppId(artifact), getSecurityId(artifact));
            }
        } catch (Exception e) {
            log.error(s("Error while registering with service discovery for [%s:%s]", nullToEmpty(getAppId(artifact)),
                    getServiceId(artifact)), e);
            throw new DeploymentException(e.getMessage());
        }
    }

    /**
     * Sets the current service as purgeable in zookeeper
     * @param artifact - The artifact being deployed
     */
    private void setPurgeable(DeploymentArtifact artifact){
        ServicePurgeClient client = null;
        String serviceName = getServiceId(artifact);
        String appName = getAppId(artifact);
        log.info("{} {} is purge-able", serviceName, appName);
        try{
            client = new ServicePurgeClient(this.zookeeperConnectionString);
            client.addPurgeService(appName, serviceName);
        } catch (Exception purgeException){
            log.error("Unable to set application: {} service: {}, as purgeable. Message: {}", appName, serviceName,
                    purgeException.getMessage());
        } finally {
            if(client != null)
                client.close();
        }
    }

    /**
     * Unsets the current service as purgeable in zookeeper
     * @param artifact - The artifact being deployed
     */
    private void unsetPurgeable(DeploymentArtifact artifact){
        ServicePurgeClient client = null;
        String serviceName = getServiceId(artifact);
        String appName = getAppId(artifact);
        try{
            client = new ServicePurgeClient(this.zookeeperConnectionString);
            client.removePurgeService(appName, serviceName);
        } catch (Exception purgeException){
            log.error("Unable to unset application: {}, service: {} as purgeable Message: {}", appName, serviceName,
                        purgeException.getMessage());
        } finally {
            if(client != null)
                client.close();
        }
    }

    /**
     * Validate the publisher's environment.  If any publishers have some environmental problem like say can not connect
     * or if a required property is missing this function should throw.  Otherwise it will not do anything.
     *
     * @throws DeploymentException   - On any invalid environment/connection
     * @throws IllegalStateException - On an illegal state such as a missing config option.
     */
    @Override
    public void validate() throws DeploymentException, IllegalStateException {
        for (Map.Entry<ArtifactType, EzPublisher> p : publishers) {
            p.getValue().validate();
        }
    }
}
