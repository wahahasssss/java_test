package com.hdu.config;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.tinkerpop.gremlin.process.traversal.Order;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.*;
import org.janusgraph.core.*;
import org.janusgraph.core.attribute.Geoshape;
import org.janusgraph.core.schema.ConsistencyModifier;
import org.janusgraph.core.schema.JanusGraphIndex;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.graphdb.database.StandardJanusGraph;
import org.janusgraph.graphdb.idmanagement.IDManager;


/**
 * @author 李兵
 * @version V1.0
 * @description TODO:
 * @date 2019/9/3 17:09
 */
@Log4j2
public class JanusGraphConfig {
    private static final String CONFIG_FILE = "conf/janusgraph-hbase-server.properties";
//    private static final String CONFIG_FILE = "conf/janusgraph-hbase-stage-server.properties";
    public final JanusGraph graph;
    public final JanusGraphManagement mgt;

    /**
     * Initialize the graph and the graph management interface.
     * 使用无参构造
     */
    public JanusGraphConfig() {


//        String path = Thread.currentThread()
//                .getContextClassLoader()
//                .getResource(CONFIG_FILE).getPath();
//
//        String decode = "";
//        try {
//            decode = URLDecoder.decode(path);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("获取配置文件路径错误原因：" + e.getLocalizedMessage());
//        }
        log.info("Connecting graph");

        org.apache.commons.configuration.Configuration configuration = new BaseConfiguration();
        configuration.setProperty("gremlin.graph", "org.janusgraph.core.JanusGraphFactory");
        configuration.setProperty("storage.backend", "hbase");
        configuration.setProperty("storage.hbase.table", "rcs:rcsGraphBuldloadTest");
        configuration.setProperty("storage.batch-loading", true);
        configuration.setProperty("storage.hostname", "127.0.0.1");
//        configuration.setProperty("storage.hbase.ext.zookeeper.znode.parent", "/m6-data-hbase/hbase");
        configuration.setProperty("graph.set-vertex-id", true);
        configuration.setProperty("cache.db-cache", true);
        configuration.setProperty("cache.db-cache-clean-wait", 20);
        configuration.setProperty("cache.db-cache-time", 180000);
        configuration.setProperty("cache.db-cache-size", 0.5);
        graph = JanusGraphFactory.open(configuration);

        log.info("Getting management");
        mgt = graph.openManagement();
//        mgt.printEdgeLabels();
//        mgt.printIndexes();
//        mgt.printPropertyKeys();
//        mgt.printVertexLabels();
//        mgt.getVertexLabels().iterator().next().longId();
    }

    //    private void dropOldKeyspace() {
//        TTransport tr = new TFramedTransport(new TSocket("localhost", 9160));
//        TProtocol proto = new TBinaryProtocol(tr);
//        Cassandra.Client client = new Cassandra.Client(proto);
//        tr.open();
//
//        client.system_drop_keyspace(JANUSGRAPH);
//        LOGGER.info("DROPPED keyspace janusgraph");
//        tr.close();
//    }


    public void close() {
        mgt.commit();
        graph.close();
    }

    public void rollback() {
        Transaction tx = graph.tx();
        tx.rollback();
    }

    public void testAddVertex(){
        //Create Schema
        JanusGraphManagement management = graph.openManagement();
        final PropertyKey name = management.makePropertyKey("name").dataType(String.class).make();
        JanusGraphManagement.IndexBuilder nameIndexBuilder = management.buildIndex("name", Vertex.class).addKey(name);
//        if (uniqueNameCompositeIndex)
//            nameIndexBuilder.unique();
        JanusGraphIndex nameIndex = nameIndexBuilder.buildCompositeIndex();
        management.setConsistency(nameIndex, ConsistencyModifier.LOCK);
        final PropertyKey age = management.makePropertyKey("age").dataType(Integer.class).make();
//        if (null != mixedIndexName)
//            management.buildIndex("vertices", Vertex.class).addKey(age).buildMixedIndex(mixedIndexName);

        final PropertyKey time = management.makePropertyKey("time").dataType(Integer.class).make();
        final PropertyKey reason = management.makePropertyKey("reason").dataType(String.class).make();
        final PropertyKey place = management.makePropertyKey("place").dataType(Geoshape.class).make();
        final PropertyKey edgeId = management.makePropertyKey("edgeId").dataType(String.class).make();
//        if (null != mixedIndexName)
//            management.buildIndex("edges", Edge.class).addKey(reason).addKey(place).buildMixedIndex(mixedIndexName);

        EdgeLabel fatherLabel = management.makeEdgeLabel("father").multiplicity(Multiplicity.MANY2ONE).make();
        management.buildIndex("edgeId", Edge.class).addKey(edgeId).unique().buildCompositeIndex();
        management.makeEdgeLabel("mother").multiplicity(Multiplicity.MANY2ONE).make();
        EdgeLabel battled = management.makeEdgeLabel("battled").signature(time).make();
        management.buildEdgeIndex(battled, "battlesByTime", Direction.BOTH, Order.desc, time);
        management.makeEdgeLabel("lives").signature(reason).make();
        management.makeEdgeLabel("pet").make();
        management.makeEdgeLabel("brother").make();

        management.makeVertexLabel("titan").make();
        management.makeVertexLabel("location").make();
        management.makeVertexLabel("god").make();
        management.makeVertexLabel("demigod").make();
        management.makeVertexLabel("human").make();
        management.makeVertexLabel("monster").make();

        management.commit();
        JanusGraphTransaction tx = graph.newTransaction();
         Vertex saturn = tx.addVertex(T.label, "titan", "name", "saturn111", "age", 10000);
         Vertex saturn1 = tx.addVertex(T.label, "titan", "name", "saturn222", "age", 1232);
         saturn.addEdge("brother",saturn1,"edgeId", "123413232");
         saturn.addEdge("brother",saturn1,"edgeId", "123413232");
         System.out.println(saturn.id());
         System.out.println(saturn.toString());
         System.out.println(saturn.property("name"));
         tx.commit();

    }
/**此方法也可以获取**/
//    public JanusGraph getJanusGraph1() {
//        JanusGraphFactory.Builder build = JanusGraphFactory.build()
//                .set("storage.backend", "cql")
//                .set("storage.cassandra.keyspace", "test")
//                .set("storage.hostname", "172.16.2.138")
//                .set("storage.port", "9042")
//                .set("index.search.backend", "elasticsearch")
//                .set("index.search.hostname", "172.16.2.137")
//                .set("cache.db-cache", "true")
//                .set("cache.db-cache-time", "3000000")
//                .set("cache.db-cache-size", "0.25");
//        JanusGraph janusGraph = build.open();
//        boolean open = janusGraph.isOpen();
//        if (open) {
//            System.out.println("janusgraph open");
//            return janusGraph;
//        }
//        return null;
//    }

public void initBatchVertexByTx(){

    int batch = 1000;
    long begin = System.currentTimeMillis();
    IDManager idManager = new IDManager();
    if (!((StandardJanusGraph)graph).getConfiguration().allowVertexIdSetting()){
        log.error("allowVertexIdSetting is {}",((StandardJanusGraph)graph).getConfiguration().allowVertexIdSetting());
        return;
    }else {
        log.info("allowVertexIdSetting is {}",((StandardJanusGraph)graph).getConfiguration().allowVertexIdSetting());
    }
    for (int i = 1; i<=1; i ++){
//        JanusGraphTransaction tx = graph.buildTransaction().customOption("graph.set-vertex-id", true).enableBatchLoading().start();
        JanusGraphTransaction tx = graph.newTransaction();



        try {
            for (int m = 0; m < batch; m++){
                 long id = idManager.toVertexId(i * batch + m);
//                Vertex companyVertex = tx.addVertex(T.label, "company_id", "companyName",
//                        String.format("company_id_%s", i * 1000 + m), "companyManageName", String.format("companyManageName_%s", i * 1000 + m),
//                        "entityValue", String.format("10%s", i * 1000 + m));
                VertexLabel companyIdLabel = tx.getVertexLabel("company_id");
                Vertex companyVertex = tx.addVertex(id, companyIdLabel);
                companyVertex.property("companyName",String.format("company_id_%s", i * batch + m));
                companyVertex.property("companyManageName", String.format("companyManageName_%s", i * batch + m));
                companyVertex.property("entityValue",  String.format("%s", i * batch + m));
                companyVertex.property("uniqueId",String.format("company_id_%s", i * batch + m));
                log.info("i is {}, param is {},cac is is {} vertexId is {}" , i,i * batch + m,id, companyVertex.id());
            }

            tx.commit();
            log.info("插入节点信息 {}，数量 {}", i, batch);
        }catch (Exception e){
            log.info("插入节点信息失败，i is {}，数量1000 {}", i, e);
            tx.rollback();
        }finally {
            if (tx.isOpen()){
                tx.close();
            }
        }

    }
    long end = System.currentTimeMillis();
    log.info("耗时：{}", (end - begin) / 1000);
}


    public void initBatchVertexPhoneByTx(){

        int batch = 1000;
        long begin = System.currentTimeMillis();
        IDManager idManager = new IDManager();
        if (!((StandardJanusGraph)graph).getConfiguration().allowVertexIdSetting()){
            log.error("allowVertexIdSetting is {}",((StandardJanusGraph)graph).getConfiguration().allowVertexIdSetting());
            return;
        }else {
            log.info("allowVertexIdSetting is {}",((StandardJanusGraph)graph).getConfiguration().allowVertexIdSetting());
        }
        for (int i = 10; i<=11; i ++){
            JanusGraphTransaction tx = graph.newTransaction();
            try {
                for (int m = 0; m < batch; m++){
                    long id = idManager.toVertexId(i * batch + m);
                    VertexLabel phoneIdLabel = tx.getVertexLabel("phone");
                    Vertex phoneVertex = tx.addVertex(id, phoneIdLabel);
                    phoneVertex.property("entityValue",  String.format("%s", i * batch + m));
                    phoneVertex.property("uniqueId",String.format("phone_%s", i * batch + m));
                    log.info("i is {}, param is {},cac is is {} vertexId is {}" , i,i * batch + m,id, phoneVertex.id());
                }
                tx.commit();
                log.info("插入节点信息 {}，数量 {}", i, batch);
            }catch (Exception e){
                log.info("插入节点信息失败，i is {}，数量1000 {}", i, e);
                tx.rollback();
            }finally {
                if (tx.isOpen()){
                    tx.close();
                }
            }

        }
        long end = System.currentTimeMillis();
        log.info("耗时：{}", (end - begin) / 1000);
    }

    public void initEdgeCompanyWithPhone(){
        int batch = 1000;
        long begin = System.currentTimeMillis();
        IDManager idManager = new IDManager();
        if (!((StandardJanusGraph)graph).getConfiguration().allowVertexIdSetting()){
            log.error("allowVertexIdSetting is {}",((StandardJanusGraph)graph).getConfiguration().allowVertexIdSetting());
            return;
        }else {
            log.info("allowVertexIdSetting is {}",((StandardJanusGraph)graph).getConfiguration().allowVertexIdSetting());
        }
        for (int i = 1; i<=1; i ++){
//        JanusGraphTransaction tx = graph.buildTransaction().customOption("graph.set-vertex-id", true).enableBatchLoading().start();
            JanusGraphTransaction tx = graph.newTransaction();



            try {
                for (int m = 0; m < batch; m++){
                    long fromId = idManager.toVertexId(i * batch + m);
                    long toId = idManager.toVertexId(10 * batch * i + m);
//                Vertex companyVertex = tx.addVertex(T.label, "company_id", "companyName",
//                        String.format("company_id_%s", i * 1000 + m), "companyManageName", String.format("companyManageName_%s", i * 1000 + m),
//                        "entityValue", String.format("10%s", i * 1000 + m));
                    Vertex fromVertex = tx.getVertex(90011000);
                    Vertex toVertex = tx.getVertex(toId);
                    fromVertex.addEdge("company_manager_phone", toVertex);
                    log.info("i is {}, fromVertex is {}, toVertex{}" , i, fromVertex.id(), toVertex.id());
                }

                tx.commit();
                log.info("插入节点信息 {}，数量 {}", i, batch);
            }catch (Exception e){
                log.info("插入节点信息失败，i is {}，数量1000 {}", i, e);
                tx.rollback();
            }finally {
                if (tx.isOpen()){
                    tx.close();
                }
            }

        }
        long end = System.currentTimeMillis();
        log.info("耗时：{}", (end - begin) / 1000);
    }
public static void dropHbaseTable(String tableName){
    try {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "10.2.5.104,10.2.3.175,10.2.4.166");
        conf.set("zookeeper.znode.parent", "/hbase-cluster/hbase");
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    } catch (Exception e) {
        e.printStackTrace();
    }

}

public void updatePro(long vertexId){
    IDManager idManager = new IDManager();
    long managerVertexId = idManager.toVertexId(vertexId);
    log.info("managerid is " + managerVertexId);
    JanusGraphTransaction tx = graph.newTransaction();
    tx.getVertex(managerVertexId);
    tx.commit();
}
    public static void main(String[] args){
        JanusGraphConfig config = new JanusGraphConfig();
//        config.initBatchVertexByTx();
//        config.initBatchVertexPhoneByTx();
        config.initEdgeCompanyWithPhone();
//        config.updatePro(1232);
        config.close();


//        dropHbaseTable("rcs:rcsGraphStage");


        log.info("task is done");
    }
}
