#!/bin/bash
#   Copyright (C) 2013-2014 Computer Sciences Corporation
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.

if [ ! -f ~vagrant/.ssh/id_rsa ]; then
  ssh-keygen -f ~vagrant/.ssh/id_rsa -N ''
  chown vagrant:vagrant ~vagrant/.ssh/id_rsa
fi

if [ ! -f ~vagrant/.openshift/express.conf ]; then
  echo "[ezbakedev] setting up openshift ssh keys so that ezdeployer can deploy"
  gem install rhc
  sudo -u vagrant mkdir -p ~vagrant/.openshift
  sudo -u vagrant cat <<EOF > ~vagrant/.openshift/express.conf
# If true, certificate errors will be ignored.
# WARNING: This may allow others to eavesdrop on your communication with OpenShift.
insecure=true

# A client certificate file for use with your server
#ssl_client_cert_file=<path_to_file>

# The SSL protocol version to use when connecting to this server
#ssl_version=<string>

# The default timeout for network operations
#timeout=<integer>

# Your OpenShift login name
default_rhlogin=admin

# A file containing CA one or more certificates
#ssl_ca_file=<path_to_file>

# If true, the server will attempt to create and use authorization tokens to connect to the server
use_authorization_tokens=true

# The OpenShift server to connect to
libra_server=broker-58b203.openshift.local
EOF
  sudo -u vagrant rhc sshkey-add -l admin -p admin ezbakedev  ~vagrant/.ssh/id_rsa.pub --confirm
  sudo -u vagrant rhc sshkey -l admin -p admin
fi
