package com.hdu.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoMapper;
import org.janusgraph.graphdb.tinkerpop.JanusGraphIoRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

/**
 * @ClassName：JanusConfiguration
 * @Description：TODO
 * @Author：ssf
 * @Date：2020/12/15 11:35 上午
 * @Versiion：1.0
 */
@Configuration
@Slf4j
public class JanusConfiguration1 {

    private volatile Cluster cluster;
    private volatile Client client;
    private volatile GraphTraversalSource graphTraversalSource;

    @Value("${graph.name}")
    private String graphName;

    @Value("${graph.hosts}")
    private String janusHosts;

    @Bean(name = "janusCluster")
    public Cluster initJanusCluster(){
        if (org.apache.commons.lang3.StringUtils.isEmpty(janusHosts)){
            return null;
        }
        String[] hosts = janusHosts.split(",");
        GryoMapper.Builder builder = GryoMapper.build().
                addRegistry(JanusGraphIoRegistry.instance());
        org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV3d0 serializer = new org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV3d0(builder);
        cluster = Cluster.build()
                .serializer(serializer)
                .maxConnectionPoolSize(20)
                .maxInProcessPerConnection(15)
                .maxWaitForConnection(3000)
                .reconnectInterval(10)
                .addContactPoints(hosts)
                .port(8182)
                .create();
        return cluster;
    }

    @Bean(name = "janusClient")
    @DependsOn(value = "janusCluster")
    public Client initJanusClient(){
        client =  cluster.connect();
        return client;
    }

    @Bean
    @DependsOn(value = "janusClient")
    public GraphTraversalSource graphTraversalSource(){
        try {
             graphTraversalSource = traversal().withRemote(DriverRemoteConnection.using(cluster, graphName+"_traversal"));
            return graphTraversalSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}