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

OSO_IP=$(</vagrant/oso_ip.txt)

cat <<EOF > /etc/dnsmasq.d/oo_dyn.conf
address=/broker-58b203.openshift.local/$OSO_IP
address=/.openshift.local/$OSO_IP
server=8.8.8.8
server=8.8.4.4
EOF

/etc/init.d/dnsmasq restart

sed -i '$ d' /etc/resolv.conf
echo "nameserver 127.0.0.1" >> /etc/resolv.conf

echo "[ezbakedev] Testing DNS"
ping -c 1 broker-58b203.openshift.local
ping -c 1 google.com
