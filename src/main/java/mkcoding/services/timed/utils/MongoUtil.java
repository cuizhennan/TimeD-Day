package mkcoding.services.timed.utils;

import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.sf.json.JSONObject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by mx on 16/3/20.
 */
public class MongoUtil {

    private static final Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    private static MongoUtil mongoSingle = null;

    public void insert(String jsonstr) {
//        Object obj = new JsonParser().parse(jsonstr);


//        Document document = new Document();
//
//        document.append("test", jsonstr);
//
//        mongoCollection.insertOne(document);
    }


    private MongoUtil() {
        if (mongoSingle == null) {
            mongoSingle = MongoHolder.mongo;
        }

        _init();
    }

    private static class MongoHolder {
        static final MongoUtil mongo = new MongoUtil();
    }

    private static void _init() {
        Properties prop = new Properties();
        try {
            prop.load(MongoUtil.class.getClassLoader().getResourceAsStream("config.properties"));

            String dbname = prop.getProperty("db.mongo.name");
            String dbcollection = prop.getProperty("db.mongo.collection");

            String host = prop.getProperty("db.mongo.host");
            String port = prop.getProperty("db.mongo.port");

            String uri = "mongodb://" + host + ":" + port;

            MongoClientURI mongoClientURI = new MongoClientURI(uri);
            MongoClient mongoClient = new MongoClient(mongoClientURI);


            MongoDatabase mongoDatabase = mongoClient.getDatabase(dbname);

            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(dbcollection);

            Document document = new Document();

            document.append("test", "hell world");

            mongoCollection.insertOne(document);

        } catch (IOException e) {
            logger.error("init ==>", e);
        }
    }
}
