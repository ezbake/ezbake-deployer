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

package ezbake.deployer.configuration;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.openshift.client.cartridge.IStandaloneCartridge;
import com.openshift.client.cartridge.StandaloneCartridge;
import ezbake.common.properties.EzProperties;
import ezbake.deployer.impl.Files;
import ezbakehelpers.ezconfigurationhelpers.system.SystemConfigurationHelper;
import ezbakehelpers.ezconfigurationhelpers.webapplication.WebApplicationConfigurationHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Configuration data related to EzDeployer configuration.
 */
public class EzDeployerConfiguration {
    private static File TMP_BASE_DIR;
    private EzProperties configuration;

    @Inject
    public EzDeployerConfiguration(Properties configuration) {
        this.configuration = new EzProperties(configuration, true);
        this.configuration.setTextCryptoProvider(
                new SystemConfigurationHelper(configuration).getTextCryptoProvider());
    }

    public Properties getEzConfiguration() {
        return this.configuration;
    }

    public File getOpenshiftGitCheckoutBaseDir() {
        String unparsedDir = getString(Keys.OPENSHIFT_GIT_CHECKOUT_BASE_DIR_KEY);
        if ( unparsedDir == null ) {
            return getGitTempBaseDir();
        } else {
            // replace things like: ${OPENSHIFT_DATA_DIR}/appRepo
            StrSubstitutor substitutor = new StrSubstitutor(System.getenv());
            File path = Files.get(substitutor.replace(unparsedDir));
            Files.createDirectories(path);
            return path;
        }
    }

    public boolean isEzFrontEndEnabled() {
        String reverseProxyName = getString(Keys.REVERSE_PROXY_APPLICATION_NAME);
        return !reverseProxyName.equalsIgnoreCase("disabled");
    }

    public String getReverseProxyApplicationName() {
        return getString(Keys.REVERSE_PROXY_APPLICATION_NAME);
    }

    public File getThriftRunnerJar() {
        Optional<String> jarString = get(Keys.THRIFT_RUNNER_JAR_KEY);
        if ( jarString.isPresent()) {
            File jarPath = new File(getString(Keys.THRIFT_RUNNER_JAR_KEY)).getAbsoluteFile();
            if ( !jarPath.canRead() )
                throw new IllegalArgumentException("The thrift runner jar does not exist or is not readable: " + jarPath);
            return jarPath;
        } else {
            return null;
        }
    }


    /**
     * Get the accumulo deployment table.  This table has the metadata dealing with the deployment artifacts.
     * Also right now it has the actual artifacts stored in a separate row.
     *
     * This property must be defined in a configuration otherwise it will throw an IllegalArgumentException
     *
     * @return tablename for the deployment table.
     * @throws java.lang.IllegalArgumentException - If the property is not defined anywhere in the configuration
     */
    public String getDeploymentTableName() {
        return getString(Keys.DEPLOYMENT_TABLE_NAME_KEY);
    }

    /**
     * Get the max write threads to use for accumulo writing
     * @return max number of threads desired to use to writing to accumulo
     */
    public int getAccumuloMaxThreads() {
        return getInt(Keys.ACCUMULO_MAX_THREADS_KEY);
    }

    /**
     * Get the latency for accumulo actions
     * @return the acceptable latency for accumulo actions
     */
    public long getAccumuloMaxLatency() {
        return getLong(Keys.ACCUMULO_MAX_LATENCY_KEY);
    }

    /**
     * Gets the max amount of memory that accumulo will use on each of its actions
     * @return the max amount of memory that accumulo will use on each of its actions
     */
    public long getAccumuloMaxMemory() {
        return getLong(Keys.ACCUMULO_MAX_MEMORY_KEY);
    }

    /**
     * @return the username of a user that can do deployments to openshift.
     */
    public String getOpenshiftUsername() {
        return getString(Keys.OPENSHIFT_USERNAME_KEY);
    }

    /**
     * @return the password of a user that can do deployments to openshift
     */
    public String getOpenshiftPassword() {
        return getString(Keys.OPENSHIFT_PASSWORD_KEY);
    }

    public int getOpenshiftTimeout() {
        return getInt(Keys.OPENSHIFT_TIMEOUT);
    }

    /**
     * @return the url to the instance's broker of OpenShift.
     */
    public String getOpenshiftHost() {
        return getString(Keys.OPENSHIFT_HOST_KEY);
    }

    /**
     * @return password to the sshkey used to git clone/pull/push to openshift.  Leave empty/undefined if the key
     * doesn't require a passphrase.
     */
    public String getOpenshiftSshPassphrase() {
        return getString(Keys.OPENSHIFT_SSH_PASSPHRASE_KEY);
    }

    public IStandaloneCartridge getOpenshiftDefaultCartridgeName() {
        return get(Keys.OPENSHIFT_DEFAULT_CARTRIDGE_NAME_KEY)
                .transform(new Function<String, IStandaloneCartridge>() {
                    @Override
                    public IStandaloneCartridge apply(String input) {
                        return new StandaloneCartridge(input);
                    }
                }).orNull();
    }

    public IStandaloneCartridge getOpenShiftJBossASCartridge() {
        return getCartridge(get(Keys.JBOSSAS_OPENSHIFT_CARTRIDGE_NAME_KEY),
                get(Keys.JBOSSAS_OPENSHIFT_CARTRIDGE_URL_KEY));
    }

    public IStandaloneCartridge getOpenShiftPlayFrameworkCartridge() {
        return getCartridge(get(Keys.PLAY_FRAMEWORK_OPENSHIFT_CARTRIDGE_NAME_KEY),
                get(Keys.PLAY_FRAMEWORK_OPENSHIFT_CARTRIDGE_URL_KEY));
    }

    public IStandaloneCartridge getOpenshiftNodeJsCartridge() {
        return getCartridge(get(Keys.NODEJS_OPENSHIFT_CARTRIDGE_NAME_KEY),
                get(Keys.NODEJS_OPENSHIFT_CARTRIDGE_URL_KEY));
    }

    public IStandaloneCartridge getOpenshiftThriftRunnerCartridge() {
        return getCartridge(get(Keys.THIRFT_RUNNER_OPENSHIFT_CARTRIDGE_NAME_KEY),
                get(Keys.THIRFT_RUNNER_OPENSHIFT_CARTRIDGE_URL_KEY));
    }

    public IStandaloneCartridge getOpenShiftWildflyCartridge() {
        return getCartridge(get(Keys.WILDFLY_OPENSHIFT_CARTRIDGE_NAME_KEY),
                get(Keys.WILDFLY_OPENSHIFT_CARTRIDGE_URL_KEY));
    }

    private IStandaloneCartridge getCartridge(final Optional<String> name, final Optional<String> url) {
        return name.transform(new Function<String, IStandaloneCartridge>() {
            @Override
            public IStandaloneCartridge apply(String input) {
                if ( url.isPresent() )
                    try {
                        return new StandaloneCartridge(input, new URL(url.get()));
                    } catch (MalformedURLException e) {
                        throw new IllegalStateException(e);
                    }
                else
                    return new StandaloneCartridge(input);
            }
        }).orNull();
    }
    /**
     * Gets a property from configuration for the given Key
     * @param key - key to get the value from
     * @return the value from configuration, or the default configured value for the key.
     *
     * @throws java.lang.IllegalStateException - if the configuration parameter is not found and the key was marked required.
     */
    private Optional<String> get(Key key) {
        if ( key.isRequired() )
            return getOrThrow(key);
        Optional<String> val = Optional.fromNullable(configuration.getProperty(key.key()));
        return val.or(key.getDefaultValue());
    }

    /**
     * Gets the value at the key, if exist.  Otherwise throws.
     * @param key - key to get the value
     * @return the value in the configuration for the given key.
     * @throws java.lang.IllegalStateException - if the configuration parameter is not found.
     */
    private Optional<String> getOrThrow(Key key) {
        String val = configuration.getProperty(key.key());
        if ( val == null )
            throw new IllegalStateException("No configuration parameter by " + key + " specified in ezConfiguration.");
        return Optional.of(val);
    }

    /**
     * Get a configuration value as a String.
     */
    private String getString(Key key) {
        Optional<String> val = get(key);
        return val.orNull();
    }

    /**
     * Get a configuration value as an int.
     * If key did not specify a default value, and its not required, will return 0 if does not exist.
     */
    private int getInt(Key key) {
        Optional<String> val = get(key);
        return val.transform(new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        }).or(0); // or 0 is if no default was defined, but the value was null and not required.
    }

    /**
     * Get a configuration value as a long.
     * If key did not specify a default value, and its not required, will return 0 if does not exist.
     */
    private long getLong(Key key) {
        Optional<String> val = get(key);
        return val.transform(new Function<String, Long>() {
            @Override
            public Long apply(String input) {
                return Long.parseLong(input);
            }
        }).or(0L); // or 0 is if no default was defined, but the value was null and not required.
    }

    /**
     * Get a configuration value as a boolean.
     * If key did not specify a default value, and its not required, will return false if does not exist.
     */
    private boolean getBoolean(Key key) {
        Optional<String> val = get(key);
        return val.transform(new Function<String, Boolean>() {
            @Override
            public Boolean apply(String input) {
                return Boolean.parseBoolean(input);
            }
        }).or(false); // or false is if no default was defined, but the value was null and not required.
    }


    public static synchronized File getGitTempBaseDir() {
        if (TMP_BASE_DIR == null) {
            TMP_BASE_DIR = generateGitTempBaseDir();
        }
        return TMP_BASE_DIR;
    }

    private static File generateGitTempBaseDir() {
        String openshiftTmpDir = OpenShiftConfiguration.EnvVariables.OPENSHIFT_TMP_DIR.getEnvValue();
        if ( openshiftTmpDir != null ) {
            return Files.createTempDirectory(Files.get(openshiftTmpDir), "appRepo");
        } else {
            return Files.createTempDirectory("appRepo");
        }
    }

    public String getUserFacingDomain() {
        WebApplicationConfigurationHelper webApp = new WebApplicationConfigurationHelper(configuration);
        return StringUtils.defaultIfEmpty(webApp.getExternalFacingDomain(), "example.local");
    }

    /** get Mesos equivalent of "large" CPU */
    public int getMesosCpuLarge() {
        return getInt(Keys.MESOS_CPU_LARGE);
    }

    /** get Mesos equivalent of "medium" CPU */
    public int getMesosCpuMedium() {
        return getInt(Keys.MESOS_CPU_MEDIUM);
    }

    /** get Mesos equivalent of "small" CPU */
    public int getMesosCpuSmall() {
        return getInt(Keys.MESOS_CPU_SMALL);
    }

    /** get Mesos equivalent of "large" MEM */
    public int getMesosMemLarge() {
        return getInt(Keys.MESOS_MEM_LARGE);
    }

    /** get Mesos equivalent of "medium" MEM */
    public int getMesosMemMedium() {
        return getInt(Keys.MESOS_MEM_MEDIUM);
    }

    /** get Mesos equivalent of "small" MEM */
    public int getMesosMemSmall() {
        return getInt(Keys.MESOS_MEM_SMALL);
    }


    /**
     * @return the URL of where asset files can be downloaded by Mesos slaves.
     */
    public String getMarathonAssetUrl() {
        return getString(Keys.MARATHON_ASSET_URL);
    }

    /**
     * @return the directory path of where tar.gz app files can be placed for web asset availability.
     */
    public String getMarathonWebDeployDir() {
        return getString(Keys.MARATHON_WEB_DEPLOY_DIR);
    }

    /**
     * @return the path of where the ThriftRunner executable jar can be found on a Mesos slave.
     */
    public String getMesosThriftRunnerPath()	{
        return getString(Keys.MESEOS_THRIFTRUNNER_PATH);
    }

    /**
     * @return Returns the base URL of where Marathon server is running.
     */
    public String getMarathonBasePath()	{
        return getString(Keys.MARATHON_BASEPATH);
    }

    /**
     * This option is here for local development.
     *
     * @return return if the deployer should use the security service
     */
    public boolean isSecurityServiceEnabled() {
        return !getBoolean(Keys.SECURITYSERVICE_DISABLE);
    }

    /**
     * @return the base URl of where the Security Service is running.
     */
    public String getSecurityServiceBasePath() {
        return getString(Keys.SECURITYSERVICE_BASEPATH);
    }

    /**
     * @return the password for the Security Service keystore.
     */
    public String getSecurityServiceKeyStorePass() {
        return getString(Keys.SECURITYSERVICE_KEYSTORE_PASS);
    }

    /**
     * @return the path including filename of the KeyStore.
     */
    public String getSecurityServiceKeyStorePath() {
        return getString(Keys.SECURITYSERVICE_KEYSTORE_PATH);
    }

    /**
     * NOTE: Be careful to use a format Java can handle.
     * @return the format of the keystore, JKS, PKCS12, etc..
     */
    public String getSecurityServiceKeyStoreFormat() {
        return getString(Keys.SECURITYSERVICE_KEYSTORE_FORMAT);
    }

    /**
     * @return the path including filename of the TrustStore.
     */
    public String getSecurityServiceTrustStorePath() {
        return getString(Keys.SECURITYSERVICE_TRUSTSTORE_PATH);
    }

    /**
     * NOTE: Be careful to use a format Java can handle.
     * @return the format of the truststore, JKS, PKCS12, etc..
     */
    public String getSecurityServiceTrustStoreFormat()	{
        return getString(Keys.SECURITYSERVICE_TRUSTSTORE_FORMAT);
    }

    /**
     * @return password of TrustStore.
     */
    public String getSecurityServiceTrustStorePass() {
        return getString(Keys.SECURITYSERVICE_TRUSTSTORE_PASS);
    }



    public static final String APPLICATION_NAMESPACE = "ezDeploy";
    private static final boolean REQUIRED = true;

    public interface Key {
        public String fullKeyPath();
        public String key();
        public String getDescription();
        public boolean isRequired();
        public Optional<String> getDefaultValue();
    }

    public enum Keys implements Key {
        // Accumulo information for use by the Deployer application
        DEPLOYMENT_TABLE_NAME_KEY("ezDeploy.accumulo.tables.deployment", Optional.of("deployment"), "The table which ez deployer stores the artifacts and indices"),
        ACCUMULO_MAX_THREADS_KEY("accumulo.max-threads", Optional.of("5"), "The number of threads to tell accumulo to use for writing/reading"),
        ACCUMULO_MAX_LATENCY_KEY("accumulo.max-latency", Optional.of("10000"), "The max latency for the batchwriter during mutations"),
        ACCUMULO_MAX_MEMORY_KEY("accumulo.max-memory", Optional.of("10000000"), "The amount of memory to use before batchwriter is forced to flush"),

        // OpenShift keys in the configuration
        OPENSHIFT_USERNAME_KEY("ezDeploy.openshift.username", REQUIRED, "The openshift username to use for the deployments.  Default development username is admin."),
        OPENSHIFT_PASSWORD_KEY("ezDeploy.openshift.password", REQUIRED, "The openshift password to use for the deployments.  Default development password is admin."),
        OPENSHIFT_HOST_KEY("ezDeploy.openshift.host", REQUIRED, "The openshift broker host to use. e.g. broker-586e.openshift.local"),
        OPENSHIFT_SSH_PASSPHRASE_KEY("ezDeploy.openshift.ssh.passphrase", Optional.<String>absent(), "SSH passphrase for pushing of artifacts to the openshift app git repository"),
        OPENSHIFT_DEFAULT_CARTRIDGE_NAME_KEY("openshift.cartridge.primary.default", Optional.of("jbossas"), "Primary cartridge name for web apps."),
        OPENSHIFT_GIT_CHECKOUT_BASE_DIR_KEY("openshift.git.checkout.base", Optional.<String>absent(),
                "A specific base local disk location to place git repositories while deploying artifacts.  " +
                        "This understand environment variables in the form of '${OPENSHIFT_DATA_DIR}/appRepo'."),
        OPENSHIFT_TIMEOUT("openshift.timeout", Optional.of("300000"), "Amount of time to wait for Open Shift to create an application"),

        // ThriftRunner configuration
        THRIFT_RUNNER_JAR_KEY("thriftrunner.jar.location", Optional.<String>absent(), "The location on disk to the thriftrunner jar file"),
        THIRFT_RUNNER_OPENSHIFT_CARTRIDGE_NAME_KEY("thriftrunner.openshift.cartridge.name", Optional.of("java-thriftrunner"), "The name of the cartridge to use for deploying thrift services to openshift"),
        THIRFT_RUNNER_OPENSHIFT_CARTRIDGE_URL_KEY("thriftrunner.openshift.cartridge.url", Optional.<String>absent(),
                "The URL to the weblocation for this cartridge code.  This is for development and using an " +
                        "experimental cartridge that isn't installed on the system."),

        JBOSSAS_OPENSHIFT_CARTRIDGE_NAME_KEY("jbossas.openshift.cartridge.name", Optional.of("jbossas"),
                "The name of the cartridge to use for deploying JBoss AS7 webapps to OpenShift"),
        JBOSSAS_OPENSHIFT_CARTRIDGE_URL_KEY("jboassas.openshift.cartridge.url", Optional.<String>absent(),
                "The URL to the weblocation for this cartridge code.  This is for development and using an " +
                        "experimental cartridge that isn't installed on the system."),

        NODEJS_OPENSHIFT_CARTRIDGE_NAME_KEY("nodejs.openshift.cartridge.name", Optional.of("nodejs-0.10"), "The name of the cartridge to use for deploying node js webapps to openshift"),
        NODEJS_OPENSHIFT_CARTRIDGE_URL_KEY("nodejs.openshift.cartridge.url", Optional.<String>absent(),
                "The URL to the weblocation for this cartridge code.  This is for development and using an " +
                        "experimental cartridge that isn't installed on the system."),

        PLAY_FRAMEWORK_OPENSHIFT_CARTRIDGE_NAME_KEY("playframework.openshift.cartridge.name", Optional.of("play-framework"),
                "The name of the cartridge to use for deploying Play Framework webapps to OpenShift"),
        PLAY_FRAMEWORK_OPENSHIFT_CARTRIDGE_URL_KEY("playframework.openshift.cartridge.url", Optional.<String>absent(),
                "The URL to the weblocation for this cartridge code.  This is for development and using an " +
                        "experimental cartridge that isn't installed on the system."),

        WILDFLY_OPENSHIFT_CARTRIDGE_NAME_KEY("wildfly.openshift.cartridge.name", Optional.of("wildfly"),
                "The name of the cartridge to use for deploying WildFly (JBoss AS8) webapps to OpenShift"),
        WILDFLY_OPENSHIFT_CARTRIDGE_URL_KEY("wildfly.openshift.cartridge.url", Optional.<String>absent(),
                "The URL to the weblocation for this cartridge code.  This is for development and using an " +
                        "experimental cartridge that isn't installed on the system."),


        // Security Service config params
        SECURITYSERVICE_TRUSTSTORE_PATH("securityservice.truststore.path", Optional.<String>absent(), ""),
        SECURITYSERVICE_TRUSTSTORE_FORMAT("securityservice.truststore.format", Optional.<String>absent(), ""),
        SECURITYSERVICE_TRUSTSTORE_PASS("securityservice.truststore.pass", Optional.<String>absent(), ""),
        SECURITYSERVICE_KEYSTORE_PATH("securityservice.keystore.path", Optional.<String>absent(), ""),
        SECURITYSERVICE_KEYSTORE_FORMAT("securityservice.keystore.format", Optional.<String>absent(), ""),
        SECURITYSERVICE_KEYSTORE_PASS("securityservice.keystore.pass", Optional.<String>absent(), ""),
        SECURITYSERVICE_BASEPATH("securityservice.rest.basepath", Optional.<String>absent(), ""),
        SECURITYSERVICE_DISABLE("securityservice.disabled", Optional.of("false"), "Disables the security service.  " +
                "No SSL certs will be added to the artifacts if this is disabled.  If disabled the securityservice properties " +
                "will be optional, otherwise they are required."),

        REVERSE_PROXY_APPLICATION_NAME("reverseProxy.thrift.applicationname", Optional.of("EzBakeFrontend"), "The ezReverseProxy service discovery application lookup name"),

        // Marathon Mesos Publisher config params
        MARATHON_ASSET_URL("marathon.asset.url", Optional.<String>absent(), ""),
        MARATHON_WEB_DEPLOY_DIR("marathon.web.deploy.dir", Optional.<String>absent(), ""),
        MESEOS_THRIFTRUNNER_PATH("mesos.thriftrunner.path", Optional.<String>absent(), ""),
        MARATHON_BASEPATH("marathon.rest.basepath", Optional.<String>absent(), ""),
        MESOS_CPU_SMALL("mesos.cpu.small", Optional.<String>absent(), ""),
        MESOS_CPU_MEDIUM("mesos.cpu.medium", Optional.<String>absent(), ""),
        MESOS_CPU_LARGE("mesos.cpu.large", Optional.<String>absent(), ""),
        MESOS_MEM_SMALL("mesos.mem.small", Optional.<String>absent(), ""),
        MESOS_MEM_MEDIUM("mesos.mem.medium", Optional.<String>absent(), ""),
        MESOS_MEM_LARGE("mesos.mem.large", Optional.<String>absent(), ""),
        ;
        private String key;
        private String description;
        private boolean required;
        private Optional<String> defaultValue;


        Keys(String key, boolean required, String description) {
            this.key = key;
            this.description = description;
            this.required = required;
            this.defaultValue = Optional.absent();
        }

        Keys(String key, Optional<String> defaultValue, String description) {
            this.key = key;
            this.description = description;
            this.required = false;
            this.defaultValue = defaultValue;
        }

        public String fullKeyPath() {
            return APPLICATION_NAMESPACE + "." + key;
        }

        public String key() {
            return key;
        }

        public String getDescription() {
            return description;
        }

        public boolean isRequired() {
            return required;
        }

        public Optional<String> getDefaultValue() {
            return defaultValue;
        }
    }

}
