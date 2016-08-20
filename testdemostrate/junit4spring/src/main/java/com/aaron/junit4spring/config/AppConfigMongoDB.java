package com.aaron.junit4spring.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by Administrator on 2016/8/20.
 */
@Configuration
@ComponentScan(basePackages = { "com.aaron.*" })
@PropertySource("classpath:dbconfig.properties")
public class AppConfigMongoDB {

    //1.2.3.4
    @Value("${mongodb.url}")
    private String mongodbUrl;

    //hello
    @Value("${mongodb.db}")
    private String defaultDb;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        MongoClientOptions mongoOptions =
                new MongoClientOptions.Builder().maxWaitTime(1000 * 60 * 5).build();
        MongoClient mongo = new MongoClient(mongodbUrl, mongoOptions);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, defaultDb);
        return new MongoTemplate(mongoDbFactory);

    }

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
