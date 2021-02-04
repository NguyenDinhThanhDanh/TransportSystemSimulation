package Databases;

import com.mongodb.ConnectionString;
import com.mongodb.Mongo;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;


public class MongoDb implements Database{
    private static MongoDatabase mongoDatabase;

    private  MongoDb(){

    }
   /**
    création de la database mongo
    @return MongoDatabase , la database mongo
    */
   public static MongoDatabase getMongoDatabase(){
       if(mongoDatabase == null){
           String uri="mongodb://0.0.0.0:27017";

           //MongoDatabase database = mongoClient.getDatabase("local");
           //MongoCollection<Document> log = database.getCollection("startup_log");
           //System.out.println("log : "+log.countDocuments());
           CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                   .register("modele")
                   .build();

           CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                   MongoClientSettings.getDefaultCodecRegistry(),
                   CodecRegistries.fromProviders(pojoCodecProvider)
           );

           MongoClient mongoClient  = MongoClients.create(new ConnectionString(uri));
           mongoDatabase = mongoClient.getDatabase("travel_details").withCodecRegistry(pojoCodecRegistry);
       }
       return mongoDatabase;
   }

}
