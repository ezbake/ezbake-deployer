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


echo "[ezbakedev] Modifying DNS name servers to allow open shift to edit our dns"

curl tasermonkeys.com/thrift-0.6.1-centos-6.4-bin.tar.gz | tar zxC /opt
cat <<EOF > /etc/profile.d/thrift.sh
export PATH=\$PATH:/opt/thrift-0.6.1/bin
EOF

yum install -y dnsmasq tree
sed -i '$ d' /etc/dnsmasq.conf
echo 'conf-dir=/etc/dnsmasq.d'>>/etc/dnsmasq.conf
