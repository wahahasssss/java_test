import java.awt.List;
import java.util.ArrayList;

import javax.management.openmbean.ArrayType;
import javax.print.Doc;
import javax.swing.text.AbstractDocument.DefaultDocumentEvent;
import org.bson.Document;
import org.bson.codecs.DoubleCodec;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDbUtil {
	private MongoClient  client= null;
	private MongoDatabase  database = null;
	private MongoCollection<Document> collection = null;
   public MongoDbUtil(String host,int port,String dbname,String collectionName)
   {
	   client = new MongoClient(host,port);
	   database = client.getDatabase(dbname);
	   collection = database.getCollection(collectionName);
   }
   public MongoDbUtil(String host,int port,String dbname) {
	client = new MongoClient(host,port);
	database = client.getDatabase(dbname);
}
   public MongoCollection<Document> collectionInstance(String collectionName)
   {
	   return database.getCollection(collectionName);
   }
   public MongoDbUtil(ArrayList<ServerAddress> sends,ArrayList<MongoCredential> credentials,String dbName)
   {
	   client = new MongoClient(sends,credentials);
	   database = client.getDatabase(dbName);
   }
}

