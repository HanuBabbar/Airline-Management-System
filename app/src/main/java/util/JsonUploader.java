//package util;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.mongodb.client.*;
//import org.bson.Document;
//
//import java.io.File;
//import java.util.List;
//
//public class JsonUploader {
//
//    private static final String JSON_FILE_PATH = "src/main/resources/users.json";
//    private static final String MONGO_URI = "mongodb://localhost:27017";
//
//    public static void uploadUsersToMongo() {
//        try (MongoClient mongoClient = MongoClients.create(MONGO_URI)) {
//            MongoDatabase database = mongoClient.getDatabase("Users"); // adjust db name if needed
//            MongoCollection<Document> collection = database.getCollection("users");
//
//            // Read and parse JSON file
//            ObjectMapper mapper = new ObjectMapper();
//            List<Document> users = mapper.readValue(
//                    new File(JSON_FILE_PATH),
//                    new TypeReference<List<Document>>() {}
//            );
//
//            // Optional: clear collection before inserting
//            collection.drop();
//
//            // Insert all documents
//            collection.insertMany(users);
//
//            System.out.println("Successfully uploaded users.json to MongoDB!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Failed to upload JSON to MongoDB.");
//        }
//    }
//
//    public static void main(String[] args) {
//        uploadUsersToMongo();
//    }
//}
