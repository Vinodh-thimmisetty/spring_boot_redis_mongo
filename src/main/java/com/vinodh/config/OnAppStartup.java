package com.vinodh.config;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.vinodh.documents.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author thimmv
 * @createdAt 21-06-2019 17:18
 */
@Component
public class OnAppStartup implements ApplicationRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private MongoClient mongoClient;
//
//    @Autowired
//    private MongoDatabase mongoDatabase;

//    @Autowired
//    private MongoCollection<User> mongoCollection;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.setIfAbsent("sequence", "1000");

        System.out.println(">>>> Current Sequence   :: " + valueOperations.get("sequence"));

        MongoCollection<Document> user = mongoOperations.getCollection("users");
        FindIterable<Document> documents = user.find(new Document("last", "Puli"));

        for (Document document : documents) {
            System.out.println(document);
        }

        for (String collectionName : mongoOperations.getCollectionNames()) {
            System.out.println("collectionName = " + collectionName);
            System.out.println("mongoOperations.getCollection(collectionName).countDocuments() = " + mongoOperations.getCollection(collectionName).countDocuments());
            MongoCollection<Document> collection = mongoOperations.getCollection(collectionName);
            for (Document document : collection.find()) {
                System.out.println("document = " + document);
            }
        }

    }
}