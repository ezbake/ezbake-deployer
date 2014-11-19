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

package ezbake.deployer.publishers.local;

import com.google.inject.Inject;
import ezbake.base.thrift.EzSecurityToken;
import ezbake.deployer.configuration.EzDeployerConfiguration;
import ezbake.deployer.utilities.ArtifactHelpers;
import ezbake.services.deploy.thrift.DeploymentArtifact;
import ezbake.services.deploy.thrift.DeploymentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class LocalFrackSubmitterPublisher extends BaseLocalPublisher {
    private static final Logger logger = LoggerFactory.getLogger(LocalThriftRunnerPublisher.class);

    @Inject
    public LocalFrackSubmitterPublisher(EzDeployerConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void publish(DeploymentArtifact artifact, EzSecurityToken callerToken) throws DeploymentException {
        //java -jar frack-submitter.jar -p {0} -i {1}'.format(pipeline["jar"], pipeline["id"])
        String appName = ArtifactHelpers.getAppId(artifact);
        String serviceName = ArtifactHelpers.getServiceId(artifact);

        try {
            String jarFile = getArtifactPath(artifact);
            String configPath = getConfigPath(artifact);
            String submitterJar = ezDeployBinDirectory + File.separator + "frack-submitter.jar";
            String command = String.format("java -jar %s -p %s -i %s", submitterJar,
                    jarFile, serviceName);
            logger.info("Executing command {}", command);
            Process process = Runtime.getRuntime().exec(command);
            dumpIO(process);
            deployments.put(appName + "_" + serviceName, new LocalDeployment(process, jarFile, configPath));

        } catch (Exception ex) {
            throw new DeploymentException("IOException " + ex.getMessage());
        }

    }
}
