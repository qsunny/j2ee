package com.aaron.springweb.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	
	@Autowired
	private Environment env;
	
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jpconfig = new JedisPoolConfig();
		jpconfig.setMaxIdle(400);
		jpconfig.setMaxWaitMillis(10000);
		jpconfig.setTestOnBorrow(true);
		jpconfig.setTestOnReturn(true);
		return jpconfig;
	}
	
	//@Bean(initMethod = "init")
	@Bean(destroyMethod = "destroy")
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
		jedisConnectionFactory.setHostName(env.getProperty("redis.host"));
		jedisConnectionFactory.setPort(Integer.valueOf(env.getProperty("redis.port")));
		jedisConnectionFactory.setPassword("0123456");
		jedisConnectionFactory.setTimeout(15000);
		jedisConnectionFactory.setUsePool(true);
		return jedisConnectionFactory;
	}
	
	@Bean
    RedisTemplate< String, Object > redisTemplate() {
        final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
        template.setConnectionFactory( jedisConnectionFactory() );
        template.setKeySerializer( new StringRedisSerializer() );
        template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        return template;
    }
	
	
	
}
