package com.aaron.springbootDemo.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@EnableCaching
@Configuration
public class RedisConfig {

	@Autowired
	private Environment env;

	@Bean
    RedisTemplate< String, Object > redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate< String, Object > template =  new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
		//template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer( new StringRedisSerializer());
        template.setHashValueSerializer( new GenericToStringSerializer<>( Object.class ) );
        template.setValueSerializer( new GenericToStringSerializer<>( Object.class ) );
        return template;
    }

	
}
