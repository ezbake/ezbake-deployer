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

package ezbake.deployer.utilities;

import ezbake.deployer.utilities.CertDataEntry;
import ezbake.services.deploy.thrift.DeploymentException;

import java.util.List;

/**
 * Get SSL Certs from the cert service
 */
public interface SSLCertsService {
    /**
     * Grab the SSL Certs from the SSL Cert service for the application id given
     *
     *
     * @param applicationId the application to grab the certs for
     * @param securityId the securityId of the application to get the certs for
     *
     * @return The SSL Certification returned by the service
     * @throws ezbake.services.deploy.thrift.DeploymentException - on any exception retrieving the certs
     */
    List<CertDataEntry> get(String applicationId, String securityId) throws DeploymentException;
}
