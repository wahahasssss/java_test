import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.Doc;
import javax.swing.text.AbstractDocument.DefaultDocumentEvent;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBExample {

    public static void main(String[] args) throws NewException {
        MongoCollection<Document> collection = null;
        try {
			/*
			MongoClient client = new MongoClient("localhost",27017);
			MongoDatabase mongoDatabase = client.getDatabase("runoob");
			System.out.println("连接数据库成功");
			MongoCollection<Document> collection = mongoDatabase.getCollection("runoob");
			Document match = new Document("bar",new Document("$gte",999998));
			FindIterable<Document> findIterable = collection.find(match);
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}
			*/
            ServerAddress sa1 = new ServerAddress("mongodb199", 60001);
            ServerAddress sa2 = new ServerAddress("mongodb199", 60002);
            ArrayList<ServerAddress> sends = new ArrayList<ServerAddress>();
            sends.add(sa1);
            sends.add(sa2);
            ArrayList<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(MongoCredential.createCredential("root", "admin", "sw3dsw2d".toCharArray()));
            MongoDbUtil mongoDbUtil = new MongoDbUtil(sends, credentials, "DcMobileAreaAppDB");
            collection = mongoDbUtil.collectionInstance("city.app.byday");
            System.out.println("连接数据库成功");

        } catch (Exception ex) {
            System.out.println("++++++++++++++++++");
            System.err.println(ex.getClass().getName() + ":" + ex.getMessage());

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
        double r = -20;
        if (r < 0) {
            throw new NewException(r);
        }
    }

}
