package com.hdu.chapter01;

import com.hdu.config.GraphSourceConfig;
import com.hdu.config.JanusGraphConfig;
import com.hdu.model.GodEntity;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerV1d0;
import org.apache.tinkerpop.gremlin.process.traversal.Order;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoMapper;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;
import org.apache.tinkerpop.gremlin.structure.util.reference.ReferenceVertex;
import org.janusgraph.core.*;
import org.janusgraph.core.attribute.Geoshape;
import org.janusgraph.core.schema.ConsistencyModifier;
import org.janusgraph.core.schema.JanusGraphIndex;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.graphdb.tinkerpop.JanusGraphIoRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName：QueryDemo
 * @Description：TODO
 * @Author：ssf
 * @Date：2020/12/10 10:40 上午
 * @Versiion：1.0
 */
@Service

public class QueryDemo {

    @Autowired
    private GraphSourceConfig graphSourceConfig;


    @Resource(name = "graphTraversalSourceWithRemoteFIle")
    private GraphTraversalSource graphTraversalSource;


    @Autowired
    private JanusGraphConfig janusGraphConfig;


//    @Autowired
//    private JanusGraph janusGraph;

    public Object queryAnyThing(String name) {
        List result = new ArrayList();
        try {
            String path = Thread.currentThread()
                    .getContextClassLoader()
                    .getResource("conf/remote.yaml").getPath();
            Cluster cluster = Cluster.open(path);
            Graph graph = EmptyGraph.instance();
            GraphTraversalSource g = graph.traversal().withRemote(DriverRemoteConnection.using(cluster, "janusgraph_traversal"));
            GraphTraversal traversal = g.V().has("name", name);
            while (traversal.hasNext()) {
                ReferenceVertex vertex = (ReferenceVertex) traversal.next();
                System.out.println(vertex.id());
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object queryWithTraversal(String name) {
        List result = new ArrayList();
        GraphTraversal traversal = graphTraversalSource.V().has("name", name);
        while (traversal.hasNext()) {
            ReferenceVertex vertex = (ReferenceVertex) traversal.next();
            System.out.println(vertex.id());
            Iterator<VertexProperty<Object>> iterator = vertex.properties("name", "age");
            while (iterator.hasNext()) {
                System.out.println(iterator.next().value());
            }
            result.add(GodEntity.builder().id(vertex.id().toString()).label(vertex.label()).build());
        }
        return result;
    }

    public Object addV() {
        GryoMapper mapper = GryoMapper.build().addRegistry(JanusGraphIoRegistry.instance()).create();
        Cluster cluster = Cluster.build().addContactPoint("127.0.0.1").port(8182).serializer(new GraphSONMessageSerializerV1d0()).create();
        Client client = cluster.connect();
        try {
            List<Result> results = client.submit("janusgraph.traversal().V()").all().get();
            System.out.println(results);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clientQuery() {
        try {
            List result = graphSourceConfig.getClient().submit("g.V()").all().get();
            System.out.println("bbbbbb");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public void clientInsertQuery() {
        try {
            StringBuilder sb = new StringBuilder();
            List result = graphSourceConfig.getClient().submit("g.V()").all().get();
            System.out.println("bbbbbb");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void source1Query() {
        try {
            graphSourceConfig.getGts1().V().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public void initSchema() {
        JanusGraphManagement management = janusGraphConfig.mgt;
        VertexLabel person = management.makeVertexLabel("person").make();
        PropertyKey name = management.makePropertyKey("name").dataType(String.class).cardinality(Cardinality.SET).make();
        PropertyKey birthDate = management.makePropertyKey("birthDate").dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        management.addProperties(person, name, birthDate);

        management.commit();


    }


    public void getLabelId() {

    }

    public void load() {

//            if (graph instanceof StandardJanusGraph) {
//                Preconditions.checkState(mixedIndexNullOrExists((StandardJanusGraph) graph, mixedIndexName),
//                        ERR_NO_INDEXING_BACKEND, mixedIndexName);
//            }

        //Create Schema
        JanusGraphManagement management = janusGraphConfig.mgt;

        /**定义属性 Property Keys**/
        final PropertyKey name = management.makePropertyKey("name").dataType(String.class).make();
        JanusGraphManagement.IndexBuilder nameIndexBuilder = management.buildIndex("name", Vertex.class).addKey(name);
//            if (uniqueNameCompositeIndex)
//                nameIndexBuilder.unique();
        JanusGraphIndex nameIndex = nameIndexBuilder.buildCompositeIndex();
        management.setConsistency(nameIndex, ConsistencyModifier.LOCK);

        final PropertyKey age = management.makePropertyKey("age").dataType(Integer.class).make();
//            if (null != mixedIndexName)
//                management.buildIndex("vertices", Vertex.class).addKey(age).buildMixedIndex(mixedIndexName);
        //时间
        final PropertyKey time = management.makePropertyKey("time").dataType(Integer.class).make();
        //原因
        final PropertyKey reason = management.makePropertyKey("reason").dataType(String.class).make();
        //地点，地方，位置
        final PropertyKey place = management.makePropertyKey("place").dataType(Geoshape.class).make();
//            if (null != mixedIndexName)
//                management.buildIndex("edges", Edge.class).addKey(reason).addKey(place).buildMixedIndex(mixedIndexName);

        /**定义边Edge Labels**/
        //父亲
        management.makeEdgeLabel("father").multiplicity(Multiplicity.MANY2ONE).make();
        //母亲
        management.makeEdgeLabel("mother").multiplicity(Multiplicity.MANY2ONE).make();
        //作战
        EdgeLabel battled = management.makeEdgeLabel("battled").signature(time).make();
        //给这个边创建索引
        management.buildEdgeIndex(battled, "battlesByTime", Direction.BOTH, Order.desc, time);
        //生活生命
        management.makeEdgeLabel("lives").signature(reason).make();
        // 宠物
        management.makeEdgeLabel("pet").make();
        // 兄弟
        management.makeEdgeLabel("brother").make();

        /**定义顶点 Vertex Lables 相当于顶一个类型*/
        management.makeVertexLabel("titan").make(); //
        management.makeVertexLabel("location").make(); //地点
        management.makeVertexLabel("god").make(); //神
        management.makeVertexLabel("demigod").make(); //半神
        management.makeVertexLabel("human").make(); //人
        management.makeVertexLabel("monster").make(); // 怪物
        //提交
        management.commit();
        /**Schema 创建完成**/


        JanusGraphTransaction tx = janusGraphConfig.graph.newTransaction();
        // vertices
        //创建一个name为saturn的顶点所属类型（标签）为titan 年龄为10000
        Vertex saturn = tx.addVertex(T.label, "titan", "name", "saturn", "age", 10000);
        Vertex sky = tx.addVertex(T.label, "location", "name", "sky");
        Vertex sea = tx.addVertex(T.label, "location", "name", "sea");
        //创建一个名为jupiter（木星）所属类型为god年龄为5000
        Vertex jupiter = tx.addVertex(T.label, "god", "name", "jupiter", "age", 5000);
        Vertex neptune = tx.addVertex(T.label, "god", "name", "neptune", "age", 4500);
        Vertex hercules = tx.addVertex(T.label, "demigod", "name", "hercules", "age", 30);
        Vertex alcmene = tx.addVertex(T.label, "human", "name", "alcmene", "age", 45);
        Vertex pluto = tx.addVertex(T.label, "god", "name", "pluto", "age", 4000);
        Vertex nemean = tx.addVertex(T.label, "monster", "name", "nemean");
        Vertex hydra = tx.addVertex(T.label, "monster", "name", "hydra");
        Vertex cerberus = tx.addVertex(T.label, "monster", "name", "cerberus");
        Vertex tartarus = tx.addVertex(T.label, "location", "name", "tartarus");

        // edges
        //为jupiter（木星）添加边及边的属性
        //木星的父亲是Saturn（土星）
        jupiter.addEdge("father", saturn);
        //木星生活账sky,原因是 的生活喜欢清新的微风
        jupiter.addEdge("lives", sky, "reason", "loves fresh breezes");
        //木星的兄弟是 海王星
        jupiter.addEdge("brother", neptune);
        //木星的另一个兄弟为冥王星
        jupiter.addEdge("brother", pluto);

        //为neptune（海王星） 添加边及边的属性
        //海王星生活中海中原因是他喜欢波浪
        neptune.addEdge("lives", sea).property("reason", "loves waves");
        //海王星的兄弟是 木星
        neptune.addEdge("brother", jupiter);
        //海王星的另一个兄弟是冥王星
        neptune.addEdge("brother", pluto);

        //为hercules（大力神）添加边及边的属性
        hercules.addEdge("father", jupiter);
        hercules.addEdge("mother", alcmene);
        hercules.addEdge("battled", nemean, "time", 1, "place", Geoshape.point(38.1f, 23.7f));
        hercules.addEdge("battled", hydra, "time", 2, "place", Geoshape.point(37.7f, 23.9f));
        hercules.addEdge("battled", cerberus, "time", 12, "place", Geoshape.point(39f, 22f));

        //为pluto（冥王星）添加边及边的属性
        pluto.addEdge("brother", jupiter);
        pluto.addEdge("brother", neptune);
        pluto.addEdge("lives", tartarus, "reason", "no fear of death");
        pluto.addEdge("pet", cerberus);

        //为地狱狗添加边
        //生活在塔耳塔洛斯
        cerberus.addEdge("lives", tartarus);

        // commit the transaction to disk
        tx.commit();

    }
}