package com.hdu.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;


/**
 * @ClassName：GraphCondfiguration
 * @Description：TODO
 * @Author：ssf
 * @Date：2020/12/11 3:12 下午
 * @Versiion：1.0
 */
@Configuration
public class GraphConfiguration {
    private String propFileName = "conf/remote-graph.properties";
    private PropertiesConfiguration conf;
    private Cluster cluster;
    private Client client;
    private static final String CONFIG_FILE = "conf/janusgraph-cql-es-server.properties";
    private JanusGraph graph;
    private JanusGraphManagement mgt;

    @Bean
    public GraphTraversalSource graphTraversalSource() throws ConfigurationException {
        conf = new PropertiesConfiguration(propFileName);
        // using the remote driver for schema
        try {
            cluster = Cluster.open(conf.getString("gremlin.remote.driver.clusterFile"));
            client = cluster.connect();
        } catch (Exception e) {
            throw new ConfigurationException(e);
        }

        // using the remote graph for queries
        return traversal().withRemote(conf);
    }

    @Bean(name = "graphTraversalSourceWithRemoteFIle")
    public GraphTraversalSource graphTraversalSource1() {
        Graph graph = EmptyGraph.instance();
        try {
            GraphTraversalSource g = graph.traversal().withRemote("conf/remote-graph.properties");


            return g;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Bean
//    public JanusGraph janusGraphManagement(){
//        PropertiesConfiguration configuration = new PropertiesConfiguration();
//        configuration.setProperty("gremlin.graph", "org.janusgraph.core.JanusGraphFactory");
//        configuration.setProperty("storage.backend", "hbase");
//        configuration.setProperty("storage.hostname", "127.0.0.1");
//        configuration.setProperty("graph.graphname", "ConfigurationManagementGraph");
//        graph = JanusGraphFactory.open(configuration);
//        return graph;
//    }
}