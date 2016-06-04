package com.aaron.winkwebapp.restful.config;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.wink.spring.Registrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.aaron.winkwebapp.api.exception.UserExceptonMapper;
import com.aaron.winkwebapp.restful.context.UserContextResolver;
import com.aaron.winkwebapp.restful.resources.UserResource;

@Configuration
@ImportResource(locations={"classpath:META-INF/server/wink-core-context.xml"})
@Import({PersistenceConfig.class})
public class WinkContextConfig {
	
	/**
	 * Resources Registrar.
		This bean is used to provide an Application object.
		The bean has the following properties:
		    - classes - returns Classes. Same as Application.getClasses().
		    			Classes can be classes either of resources or providers.
		    			The scope is used according default framework definition.
		    - instances - returns instances of either resources or providers. Instances
		    			are actually Spring beans and their scope is controled using Spring.
		    - priority - priority of the Registrar. When omitted, the default value is 0.5
	 * @return Registrar
	 */
	@Bean
	public Registrar register() {
		Registrar register = new Registrar();
		Set<Class<?>> classes = new HashSet<Class<?>>();
		
		Set<Object> instances = new HashSet<Object>();
		
		//resources
		instances.add(userResource());
		
		//providers
		instances.add(userProvider());
		instances.add(new UserExceptonMapper());
		
		register.setClasses(classes);
		register.setInstances(instances);
		return register;
	}
	
	@Bean
	public UserResource userResource() {
		return new UserResource();
	}
	
	@Bean
	public UserContextResolver userProvider() {
		return new UserContextResolver();
	}
	
	@Bean
	public PropertiesFactoryBean propertiesFactoryBean() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		Resource location = new ClassPathResource("winkwebapp-restful.properties");
		Resource[] locations = new Resource[] {location};
		propertiesFactoryBean.setLocations(locations);
		
		return propertiesFactoryBean;
	}
	
	@Bean
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer PropertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		PropertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		PropertyPlaceholderConfigurer.setOrder(1);
		Properties properties = new Properties();
		properties.put("winkPropertiesFactory", propertiesFactoryBean());
		PropertyPlaceholderConfigurer.setPropertiesArray(properties);
		return PropertyPlaceholderConfigurer;
	}

}
