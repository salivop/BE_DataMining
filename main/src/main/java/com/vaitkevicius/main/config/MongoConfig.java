package com.vaitkevicius.main.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${mongo.url:localhost}")
    private String url;
    @Value("${mongo.port:27017}")
    private int port;
    @Value("${mongo.db:dataMining}")
    private String db;

    @Bean
    public MongoClient mongoClient() { return new MongoClient(url, port);}

    @Bean
    @Autowired
    public MongoTemplate mongoTemplate(MongoClient mongoClient) { return new MongoTemplate(mongoClient, db); }
}
