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

package ezbake.deployer;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import ezbake.deployer.configuration.EzDeployerConfiguration;
import ezbake.deployer.publishers.*;
import ezbake.deployer.publishers.database.*;
import ezbake.ezdiscovery.ServiceDiscovery;
import ezbake.ezdiscovery.ServiceDiscoveryClient;
import ezbake.security.client.EzbakeSecurityClient;
import ezbake.thrift.ThriftClientPool;

import java.util.Properties;

public class DeployerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EzPublisherMapping.class);
        bind(ServiceDiscovery.class).to(ServiceDiscoveryClient.class);
        bind(EzDeployerConfiguration.class).in(Singleton.class);
        bind(EzDeployerStore.class).to(AccumuloEzDeployerStore.class);
        Multibinder<DatabaseSetup> binder = Multibinder.newSetBinder(binder(), DatabaseSetup.class);
        binder.addBinding().to(MongoDBDatabaseSetup.class);
        binder.addBinding().to(PostgresDatabaseSetup.class);
        binder.addBinding().to(AccumuloDatabaseSetup.class);
        binder.addBinding().to(MonetDBDatabaseSetup.class);
        binder.addBinding().to(ElasticsearchDatabaseSetup.class);
        binder.addBinding().to(TitanDatabaseSetup.class);
    }

    @Provides
    ServiceDiscoveryClient provideServiceDiscoveryClient(Properties configuration) {
        return new ServiceDiscoveryClient(configuration);
    }

    @Provides @Singleton
    ThriftClientPool provideThriftClientPool(Properties configuration) {
        return new ThriftClientPool(configuration);
    }

    @Provides @Singleton
    EzbakeSecurityClient provideEzbakeSecurityClient(Properties configuration) {
        return new EzbakeSecurityClient(configuration);
    }
}
