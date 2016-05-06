package com.aaron.springweb.web.config;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan(basePackages = {
		"com.aaron.springweb.core" }, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
				"com.aaron.springweb.web.*" }))
@Import({PersistenceConfig.class,RedisConfig.class})
//@PropertySource(value="classpath:application-${spring.profiles.active}.properties",ignoreResourceNotFound=true)
//@EnableScheduling
//@EnableAspectJAutoProxy
//@EnableCaching
//@Profile("prod")
public class AppConfig {
	
	private static final Logger log = Logger.getLogger(AppConfig.class);
	
	@Autowired
	private Environment env;
	
	@Configuration
	@PropertySource(value="classpath:application-${spring.profiles.active}.properties",ignoreResourceNotFound=true)
	@Profile("dev")
	public static class devConfig {
		
	}
	
	@Configuration
	@PropertySource(value="classpath:application-${spring.profiles.active}.properties",ignoreResourceNotFound=true)
	@Profile("prod")
	public static class prodConfig {
		
	}
	
/*	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}*/

	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		log.info("|||||||||||||||||||||"+env.getProperty("smtp.host"));
		mailSenderImpl.setHost(env.getProperty("smtp.host"));
		mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
		mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
		mailSenderImpl.setUsername(env.getProperty("smtp.username"));
		mailSenderImpl.setPassword(env.getProperty("smtp.password"));

		Properties javaMailProps = new Properties();
		javaMailProps.put("mail.smtp.auth", true);
		javaMailProps.put("mail.smtp.starttls.enable", true);

		mailSenderImpl.setJavaMailProperties(javaMailProps);

		return mailSenderImpl;
	}

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
}
