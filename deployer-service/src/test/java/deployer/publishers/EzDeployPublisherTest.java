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

package deployer.publishers;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import ezbake.configuration.constants.EzBakePropertyConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.test.TestingServer;
import deployer.TestUtils;
import ezbake.base.thrift.EzSecurityToken;
import ezbake.deployer.publishers.EzDeployPublisher;
import ezbake.deployer.publishers.EzPublisher;
import ezbake.deployer.publishers.EzPublisherMapping;
import ezbake.deployer.utilities.SSLCertsService;
import ezbake.ezdiscovery.ServiceDiscovery;
import ezbake.services.deploy.thrift.ArtifactType;
import ezbake.services.deploy.thrift.DeploymentArtifact;
import ezbake.thrift.ThriftTestUtils;
import org.easymock.Capture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.Properties;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.capture;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EzDeployPublisherTest {
    private TestingServer server;
    private CuratorFramework zkClient;
    public final static String PURGE_NAMESPACE = "ezpurge";
    @Before
    public void setup() throws Exception {
        System.setProperty("curator-dont-log-connection-problems", "true");
        server = new TestingServer();
        zkClient = CuratorFrameworkFactory.builder()
                .connectString(server.getConnectString())
                .namespace(PURGE_NAMESPACE)
                .retryPolicy(new RetryNTimes(5, 1000))
                .build();
        zkClient.start();
    }

    @After
    public void teardown() {
        try {
            server.close();
            zkClient.close();
        } catch(Throwable ignored) { }
    }
    @Parameterized.Parameters(name = "publish artifact {0}")
    public static Collection<Object[]> data() {
        return Lists.newArrayList(Iterables.transform(Lists.newArrayList(ArtifactType.values()), new Function<ArtifactType, Object[]>() {
            @Override
            public Object[] apply(ArtifactType artifactType) {
                return new Object[]{artifactType};
            }
        }));
    }
    @Parameterized.Parameter(value = 0)
    public ArtifactType artifactType;

    @Test
    public void testPublish() throws Exception {
        assertEquals(0, zkClient.getChildren().forPath("").size());
        EzPublisher expectedPublisher = createMock(EzPublisher.class);
        EzPublisher unexpectedPublisher = createMock(EzPublisher.class);

        EzPublisherMapping mapping = createMock(EzPublisherMapping.class);

        SSLCertsService certsService = createMock(SSLCertsService.class);
        Capture<DeploymentArtifact> deploymentArtifactCapture = new Capture<>();
        Capture<EzSecurityToken> tokenCapture = new Capture<>();
        ServiceDiscovery serviceDiscovery = createMock(ServiceDiscovery.class);

        Properties props = new Properties();
        props.setProperty(EzBakePropertyConstants.ZOOKEEPER_CONNECTION_STRING, server.getConnectString());
        EzDeployPublisher publisher = new EzDeployPublisher(mapping, certsService, props, serviceDiscovery, null);
        expect(mapping.get(anyObject(ArtifactType.class)))
                .andAnswer(TestUtils.singleAnswerFor(artifactType, expectedPublisher, unexpectedPublisher)).anyTimes();
        expect(certsService.get(TestUtils.SERVICE_NAME, TestUtils.SECURITY_ID)).andReturn(TestUtils.sampleSSL());
        serviceDiscovery.setSecurityIdForApplication(TestUtils.APP_NAME, TestUtils.SECURITY_ID);

        expectedPublisher.publish(capture(deploymentArtifactCapture), capture(tokenCapture));

        replay(expectedPublisher, unexpectedPublisher, mapping, certsService, serviceDiscovery);
        publisher.publish(TestUtils.createSampleDeploymentArtifact(artifactType), ThriftTestUtils.generateTestSecurityToken("U"));

        verify(expectedPublisher, unexpectedPublisher, certsService, serviceDiscovery);

        TestUtils.assertDeploymentArtifact(deploymentArtifactCapture.getValue(), artifactType);

        TestUtils.getSampleTarBallChecker(artifactType).check(deploymentArtifactCapture.getValue().getArtifact());
        //test to ensure that a purgeable services was registered
        assertEquals(1, zkClient.getChildren().forPath("").size());
    }


    /**
     * Pretty dumb test but just ensure that the forwarding to the PublisherMapping works for unpublish.
     *
     * @throws Exception on any testing errors
     */
    @Test
    public void testUnPublish() throws Exception {
        EzPublisher expectedPublisher = createMock(EzPublisher.class);
        EzPublisher unexpectedPublisher = createMock(EzPublisher.class);

        SSLCertsService certsService = createMock(SSLCertsService.class);
        EzPublisherMapping mapping = createMock(EzPublisherMapping.class);
        ServiceDiscovery serviceDiscovery = createMock(ServiceDiscovery.class);
        Properties props = new Properties();
        props.setProperty(EzBakePropertyConstants.ZOOKEEPER_CONNECTION_STRING, server.getConnectString());
        EzDeployPublisher publisher = new EzDeployPublisher(mapping, certsService, props, serviceDiscovery, null);
        Capture<DeploymentArtifact> deploymentArtifactCapture = new Capture<>();
        Capture<EzSecurityToken> tokenCapture = new Capture<>();

        expect(mapping.get(anyObject(ArtifactType.class)))
                .andAnswer(TestUtils.singleAnswerFor(artifactType, expectedPublisher, unexpectedPublisher)).anyTimes();
        expectedPublisher.unpublish(capture(deploymentArtifactCapture), capture(tokenCapture));

        replay(expectedPublisher, unexpectedPublisher, mapping, certsService, serviceDiscovery);
        publisher.unpublish(TestUtils.createSampleDeploymentArtifact(artifactType), ThriftTestUtils.generateTestSecurityToken("U"));

        verify(expectedPublisher, unexpectedPublisher, certsService, serviceDiscovery);
        //test to ensure there are no purgeable services registered
        assertEquals(0, zkClient.getChildren().forPath("").size());
    }

}
