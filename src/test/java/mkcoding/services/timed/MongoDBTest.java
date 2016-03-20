package mkcoding.services.timed;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mx on 16/3/20.
 */
@RestController
@RequestMapping("/mongo")
public class MongoDBTest {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBTest.class);

    private MongoCollection<Document> collection = null;

    public MongoDBTest() {
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase database = mongoClient.getDatabase("d-day");
        collection = database.getCollection("timed-day");
    }

    @RequestMapping("/find")
    public String testMongo() {
        logger.info("mongo query start...");
        return collection.find().first().toJson();
    }

//    public static void main(String[] args) {
//        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017");
//
//        MongoClient mongoClient = new MongoClient(mongoClientURI);
//
//        MongoDatabase database = mongoClient.getDatabase("d-day");
//
//    }
}
