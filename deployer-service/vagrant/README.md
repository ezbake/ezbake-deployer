EzDeployer Vagrant
-------
This directory of ezdeployer should help developing against ezdeployer. This vagrant setup is configured to launch 2 different VM's.<br/>

* openshift
* ezbakedev

## openshift
This VM is a prebuild openshift VM. This will have the Openshift broker, the console, the thriftrunner cartridge, and the JBoss Cartridge preinstall.<br/>
It has these preconfigured properties:

* HOSTNAME: broker-58b203.openshift.local
* Public: Its on the public network, and thus a dynamic IP
* Web Console URL: https://broker-58b203.openshift.local  (should be accessable from your Mac Browser, windows you have to install a zeroconf/mdns to have this work)
  * Username: admin
  * Password: admin
* vagrant ssh openshift
  * Should let you ssh into the machine

If your network connection drops, and you get a new IP you can update the IP by doing:  

```
vagrant ssh openshift -c "sudo /vagrant/get_oso_ip.sh"
```

## ezbakedev
This VM is based off of the ezbake-dev-tool ezbake base vagrant image
It then runs a few provisioning to gather the preconfigured ezconfig configuration and place them in /etc/sysconfig/ezbake, update the dns to allow for \*.openshift.local to point to the other vm

If the IP gets stale for the other box(and you ran the command in the openshift section to update the IP) you can run this command to refresh the DNS to see the new IP:

```
vagrant ssh -c "sudo /vagrant/update_ezbake_dns.sh"
```

## How to use

### Prereqs
You will need  [VirtualBox](http://virtualbox.org) - This is a VM provider<br/>
You will then need [Vagrant](http://www.vagrantup.com/) - Development VMs made easy.  This allows us to setup your dev enviorment automagically

Now that you have Vagrant and VirtualBox installed, you _should_ install vagrant cachier plugin:
This plugin, caches RPMs for yum/apt-get repositories, and a few other sources, this makes it quicker to reprovision vagrant VMs in the future.

```
vagrant plugin install vagrant-cachier
```

Now, due to the complexity of the system, we have created 2 Vagrant base boxes that has the dev enviorment mostly preconfigured, you will need to add them to your local cache.
*NOTE: The box add will take a long time to download due to their sizes.*

* ezbake.box = 655.12 MB
* openshift-3.box = 2.4G

```
vagrant box add ezbake <download url here> --insecure
vagrant box add openshift-3 <download url here> --insecure
vagrant box list
```

The vagrant box list should list atleast 2 boxes (depending on your other vagrant vms it may be more)<br/>
Mine look like this, noteing the important ones here is openshift-3, and ezbake

```
CentOS-6.4-x86_64-v20130731 (virtualbox)
lucid32                     (virtualbox)
lucid64                     (virtualbox)
openshift-3                 (virtualbox)
ezbake                      (virtualbox)
precise64                   (virtualbox)
precise42                   (virtualbox)
```

# Instructions

If you haven't already, checkout the project:

```
git clone git@github.com:ezbake/ezbake-deployer.git
```

Then go into the ./ezdeploy/vagrant directory<br/>
Then now, you should be able to run(this command will take some time due to the sizes of the VMs)

```
vagrant up
```

And this should output something like:

```
Bringing machine 'openshift' up with 'virtualbox' provider...
Bringing machine 'ezbakedev' up with 'virtualbox' provider...
[openshift] Importing base box 'openshift-3'...
[openshift] Matching MAC address for NAT networking...
[openshift] Setting the name of the VM...
[openshift] Clearing any previously set forwarded ports...
[openshift] Fixed port collision for 22 => 2222. Now on port 2201.
[openshift] Clearing any previously set network interfaces...
[openshift] Preparing network interfaces based on configuration...
[openshift] Forwarding ports...
[openshift] -- 22 => 2201 (adapter 1)
[openshift] Running 'pre-boot' VM customizations...
[openshift] Booting VM...
[openshift] Waiting for machine to boot. This may take a few minutes...
[openshift] Machine booted and ready!
[openshift] Configuring and enabling network interfaces...
[openshift] Mounting shared folders...
[openshift] -- /vagrant
[openshift] -- /home/vagrant/workspaces
[openshift] Running provisioner: shell...
[openshift] Running: /var/folders/7r/8dqwdn9j4znb63w8xp1fbvmr0000gp/T/vagrant-shell20140208-90154-1lidbgb
[ezbakedev] Importing base box 'ezbake'...
[ezbakedev] Matching MAC address for NAT networking...
[ezbakedev] Setting the name of the VM...
[ezbakedev] Clearing any previously set forwarded ports...
[ezbakedev] Fixed port collision for 22 => 2201. Now on port 2202.
[ezbakedev] Clearing any previously set network interfaces...
[ezbakedev] Preparing network interfaces based on configuration...
[ezbakedev] Forwarding ports...
[ezbakedev] -- 22 => 2202 (adapter 1)
[ezbakedev] -- 2181 => 2181 (adapter 1)
[ezbakedev] Running 'pre-boot' VM customizations...
[ezbakedev] Booting VM...
[ezbakedev] Waiting for machine to boot. This may take a few minutes...
[ezbakedev] Machine booted and ready!
[ezbakedev] Setting hostname...
[ezbakedev] Configuring and enabling network interfaces...
[ezbakedev] Mounting shared folders...
[ezbakedev] -- /vagrant
[ezbakedev] -- /home/vagrant/ezdeployer
[ezbakedev] Running provisioner: shell...
[ezbakedev] Running: inline script
[ezbakedev] Running provisioner: shell...
[ezbakedev] Running: /var/folders/7r/8dqwdn9j4znb63w8xp1fbvmr0000gp/T/vagrant-shell20140208-90154-zuiv5a
[ezbakedev] Modifying DNS name servers to allow open shift to edit our dns
Loaded plugins: fastestmirror
Determining fastest mirrors
 * base: mirror.teklinks.com
 * extras: centos.aol.com
 * rpmforge: mirror.teklinks.com
 * updates: centos.mirror.nac.net
Setting up Install Process
Resolving Dependencies
--> Running transaction check
---> Package dnsmasq.x86_64 0:2.48-13.el6 will be installed
---> Package tree.x86_64 0:1.5.3-2.el6 will be installed
--> Finished Dependency Resolution

Dependencies Resolved

================================================================================
 Package           Arch             Version                Repository      Size
================================================================================
Installing:
 dnsmasq           x86_64           2.48-13.el6            base           149 k
 tree              x86_64           1.5.3-2.el6            base            36 k

Transaction Summary
================================================================================
Install       2 Package(s)

Total download size: 185 k
Installed size: 358 k
Downloading Packages:
--------------------------------------------------------------------------------
Total                                           320 kB/s | 185 kB     00:00
Running rpm_check_debug
Running Transaction Test
Transaction Test Succeeded
Running Transaction
Warning: RPMDB altered outside of yum.
  Installing : dnsmasq-2.48-13.el6.x86_64                                   1/2
  Installing : tree-1.5.3-2.el6.x86_64                                      2/2
  Verifying  : tree-1.5.3-2.el6.x86_64                                      1/2
  Verifying  : dnsmasq-2.48-13.el6.x86_64                                   2/2

Installed:
  dnsmasq.x86_64 0:2.48-13.el6             tree.x86_64 0:1.5.3-2.el6

Complete!
[ezbakedev] Running provisioner: shell...
[ezbakedev] Running: /var/folders/7r/8dqwdn9j4znb63w8xp1fbvmr0000gp/T/vagrant-shell20140208-90154-3b2h8o
[ezbakedev] Running provisioner: shell...
[ezbakedev] Running: /var/folders/7r/8dqwdn9j4znb63w8xp1fbvmr0000gp/T/vagrant-shell20140208-90154-s2aps7

Starting dnsmasq: [  OK  ]
[ezbakedev] Testing DNS
PING broker-58b203.openshift.local (10.10.1.10) 56(84) bytes of data.
64 bytes from 10.10.1.10: icmp_seq=1 ttl=63 time=0.077 ms

--- broker-58b203.openshift.local ping statistics ---
1 packets transmitted, 1 received, 0% packet loss, time 38ms
rtt min/avg/max/mdev = 0.077/0.077/0.077/0.000 ms
PING google.com (74.125.228.104) 56(84) bytes of data.
64 bytes from iad23s08-in-f8.1e100.net (74.125.228.104): icmp_seq=1 ttl=63 time=21.3 ms

--- google.com ping statistics ---
1 packets transmitted, 1 received, 0% packet loss, time 47ms
rtt min/avg/max/mdev = 21.320/21.320/21.320/0.000 ms
```

OK Now we have the machines started up you have the options of logging into the vagrant boxes.

To log into the openshift box do:

```
vagrant ssh openshift
```

To log into the ezbakedev box do:

```
vagrant ssh
```

### Compiling and running ezdeployer
Ok, Now from within the ezbakedev box:<br/>

```
cd ezdeployer
mvn -Pvagrant package
```

Assuming success, you should see BUILD SUCCESS.<br/>
Now we can cd into the directory that this built that is ready to be ran.
cd target/vagrant-vagrant.dir
./bin/ezdeployerd &> ezdeployerd.log &

Now we can tail the logs to make sure it starts up:

    tail -f ezdeployerd.log

If this did work, it should have this printout:

    ThreadedPool has started on EzbakeVagrant:36998
    Detected Processor was class ezbake.services.deploy.thrift.EzBakeServiceDeployer$Processor


Now assuming success, you can run the cli:

    ./bin/ezdeployer-cli ping


If you want to stop the process you can try this command:

```
kill $(pgrep ezde)
```

### troublshooting

#### Zookeeper error

When you run ezdeployerd, ff you get this:


```
    Exception in thread "main" org.apache.zookeeper.KeeperException$ConnectionLossException: KeeperErrorCode = ConnectionLoss
	at com.netflix.curator.ConnectionState.getZooKeeper(ConnectionState.java:72)
	at com.netflix.curator.CuratorZookeeperClient.getZooKeeper(CuratorZookeeperClient.java:74)
	at com.netflix.curator.framework.imps.CuratorFrameworkImpl.getZooKeeper(CuratorFrameworkImpl.java:372)
	at com.netflix.curator.framework.imps.DeleteBuilderImpl$3.call(DeleteBuilderImpl.java:159)
	at com.netflix.curator.framework.imps.DeleteBuilderImpl$3.call(DeleteBuilderImpl.java:155)
	at com.netflix.curator.RetryLoop.callWithRetry(RetryLoop.java:85)
	at com.netflix.curator.framework.imps.DeleteBuilderImpl.pathInForeground(DeleteBuilderImpl.java:151)
	at com.netflix.curator.framework.imps.DeleteBuilderImpl.forPath(DeleteBuilderImpl.java:136)
	at com.netflix.curator.framework.imps.DeleteBuilderImpl.forPath(DeleteBuilderImpl.java:34)
	at ezbake.ezdiscovery.ServiceDiscoveryClient.registerEndpoint(ServiceDiscoveryClient.java:67)
	at ezbake.ezdiscovery.ServiceDiscoveryClient.registerEndpoint(ServiceDiscoveryClient.java:49)
	at ezbake.thriftrunner.ThriftRunner.registerEndpointWithServiceDiscovery(ThriftRunner.java:258)
	at ezbake.thriftrunner.ThriftRunner.run(ThriftRunner.java:134)
	at ezbake.thriftrunner.ThriftRunner.main(ThriftRunner.java:315)
```

You might double check zookeeper:
if the first command error outs, then run the second.

```
/opt/zookeeper/bin/zkCli.sh
sudo /opt/zookeeper/bin/zkServer.sh restart
```
Then you can retry to start ezdeployerd.  But make sure to kill your previous one first... 

Then you can re run it by following the same instructions above.

#### Maven not using artifactory

If you noticed that its not finding artifacts, and not trying to download from the artifact repository

The easiest way to fix this is to add this snibbit to your ~/.vagrant.d/VagrantFile

```
   Vagrant.configure("2") do |config|
      config.vm.synced_folder "~/.m2", "/home/vagrant/.m2"
   end
```
