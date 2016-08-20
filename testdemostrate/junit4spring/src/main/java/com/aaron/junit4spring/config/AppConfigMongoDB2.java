package com.aaron.junit4spring.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by Administrator on 2016/8/20.
 */
@Configuration
@ComponentScan(basePackages = { "com.aaron.*" })
@PropertySource(value={"classpath:dbconfig.properties"},ignoreResourceNotFound=true)
/*@PropertySources({
        @PropertySource("classpath:config.properties"),
        @PropertySource("classpath:dbconfig.properties")
})*/
public class AppConfigMongoDB2 {
    @Autowired
    private Environment env;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        String mongodbUrl = env.getProperty("mongodb.url");
        String defaultDb = env.getProperty("mongodb.db");

        MongoClientOptions mongoOptions =
                new MongoClientOptions.Builder().maxWaitTime(1000 * 60 * 5).build();
        MongoClient mongo = new MongoClient(mongodbUrl, mongoOptions);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, defaultDb);
        return new MongoTemplate(mongoDbFactory);

    }


}
