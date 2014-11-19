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

package ezbake.deployer.cli;

public class UsageException extends RuntimeException {
    public final Command cmd;
    public final String name;
    public final String errorMessage;


    public UsageException(Command cmd, String name, String errorMessage) {
        super(errorMessage);
        this.cmd = cmd;
        this.name = name;
        this.errorMessage = errorMessage;

    }

}
