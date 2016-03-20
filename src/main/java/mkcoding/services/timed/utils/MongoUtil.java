package mkcoding.services.timed.utils;

import com.google.gson.JsonParser;
import com.mongodb.ServerAddress;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.*;
import com.mongodb.connection.ClusterSettings;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by mx on 16/3/20.
 */
public class MongoUtil {

    private static final Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    private static MongoUtil mongoSingle = null;

    private static MongoCollection<Document> mongoCollection = null;

    public static void insert(String jsonstr) {
        Object obj = new JsonParser().parse(jsonstr);

        Document document = null;

        mongoCollection.insertOne(document, new SingleResultCallback<Void>() {
            @Override
            public void onResult(Void aVoid, Throwable throwable) {
                System.out.println(aVoid);
                logger.info("insert success.");
            }
        });
    }


    private MongoUtil() {
        if (mongoSingle == null) {
            mongoSingle = MongoHolder.mongo;
        }

        _init();
    }

    private static class MongoHolder {
        private static final MongoUtil mongo = new MongoUtil();
    }

    private void _init() {
        Properties prop = new Properties();
        MongoClient mongoClient = null;
        try {
            prop.load(MongoUtil.class.getClassLoader().getResourceAsStream("config.properties"));

            String dbname = prop.getProperty("db.mongo.name");
            String dbcollection = prop.getProperty("db.mongo.collection");

            String host = prop.getProperty("db.mongo.host");
            String port = prop.getProperty("db.mongo.port");

            ClusterSettings clusterSettings = ClusterSettings.builder().hosts(Arrays.asList(new ServerAddress(host))).build();
            MongoClientSettings settings = MongoClientSettings.builder().clusterSettings(clusterSettings).build();

            mongoClient = MongoClients.create(settings);//默认链接端口号


            MongoDatabase mongoDatabase = mongoClient.getDatabase(dbname);

            mongoCollection = mongoDatabase.getCollection(dbcollection);
        } catch (IOException e) {
            logger.error("init ==>", e);
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }
}
