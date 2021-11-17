import java.util.ArrayList;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class MongoDbTest {
    @Test
    public void ttttt() {
        MongoCollection<Document> collection = null;
        try {

            ServerAddress sa1 = new ServerAddress("mongodb199", 60001);
            ServerAddress sa2 = new ServerAddress("mongodb199", 60002);
            ArrayList<ServerAddress> sends = new ArrayList<ServerAddress>();
            sends.add(sa1);
            sends.add(sa2);
            ArrayList<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(MongoCredential.createCredential("root", "admin", "sw3dsw2d".toCharArray()));
            MongoDbUtil mongoDbUtil = new MongoDbUtil(sends, credentials, "DcMobileAreaAppDB1");
            collection = mongoDbUtil.collectionInstance("city.app.byday");
            System.out.println("连接数据库成功");

        } catch (Exception e) {
            System.out.println("++++++++++++++++++");
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
        }

        Document match = new Document("$match", new Document("city", "0601").append("date", 20161014));

        //FindIterable<Document> results = collection.find(match);
        Document group = new Document("$group", new Document("_id", "$app").append("sum", new Document("$sum", 1)));
        ArrayList<Document> list = new ArrayList<Document>();
        list.add(match);
        list.add(group);
        DBObject matchfield = new BasicDBObject("$match", new BasicDBObject("city", "0601"));
        DBObject groupfield = new BasicDBObject("$group", new BasicDBObject("_id", "$app").put("sum", new BasicDBObject("$sum", 1)));

        AggregateIterable<Document> results = collection.aggregate(list);
        for (Document document : results) {
            System.out.println(document.get("_id").toString());
            ;
        }
        MongoCursor<Document> mongoCursor = results.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }

}

