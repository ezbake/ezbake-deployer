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

package ezbake.deployer.cli.commands;
import ezbake.services.deploy.thrift.DeploymentException;
import ezbake.services.deploy.thrift.DeploymentMetadata;
import org.apache.thrift.TException;

import java.io.IOException;

public class LatestVersionCommand extends BaseCommand {

    @Override
    public void call() throws IOException, TException, DeploymentException {
        String[] args = globalParameters.unparsedArgs;
        minExpectedArgs(2, args, this);
        String applicationId = args[0];
        String serviceId = args[1];
        DeploymentMetadata result = getClient().getLatestApplicationVersion(applicationId, serviceId, getSecurityToken());

        System.out.println("Latest Application Version: " + result.toString());
    }

    @Override
    public String getName() {
        return "latestVersion";
    }


    @Override
    public void displayHelp() {
        System.out.println(getName() + " <applicationId> <serviceId>");
        System.out.println("\tPrints out the latest version's metadata of the application specified");
    }

    @Override
    public String quickUsage() {
        return getName() + " - retrieves the application information of the latest version of the application";
    }
}
