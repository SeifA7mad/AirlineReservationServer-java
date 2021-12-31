package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {

    private static MongoClientURI clientURI;
    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;

    public static void createDatabaseConnection() {
        // clientURI = new MongoClientURI(
        //         "mongodb://Airline:123@cluster0-shard-00-00.i4kcn.mongodb.net:27017,cluster0-shard-00-01.i4kcn.mongodb.net:27017,cluster0-shard-00-02.i4kcn.mongodb.net:27017/AirlineReservationDB?ssl=true&replicaSet=atlas-9sogdi-shard-0&authSource=admin&retryWrites=true&w=majority");
        mongoClient = new MongoClient("localhost", 27017);
        mongoDatabase = mongoClient.getDatabase("AirlineReservationDB");
        
    }
    
    public static MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public static void createCollection(String collectionName) {
        mongoDatabase.createCollection(collectionName);
    }

    public static MongoCollection getCollection(String collectionName) {
        return mongoDatabase.getCollection(collectionName);
    }
}
