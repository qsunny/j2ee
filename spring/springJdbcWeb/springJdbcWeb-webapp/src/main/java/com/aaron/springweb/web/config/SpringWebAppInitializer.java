package com.aaron.springweb.web.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("spring.profiles.active", "prod");
/*	     FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
	      encodingFilter.setInitParameter("encoding", "UTF-8");
	      encodingFilter.setInitParameter("forceEncoding", "true");
	      encodingFilter.addMappingForUrlPatterns(null, false, "/*");*/		
		
		super.onStartup(servletContext);
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("utf-8");
		encodingFilter.setForceEncoding(true);
		Filter[] filters = new Filter[]{encodingFilter};
		
		return filters;
	}
	
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	/*	
  	@Configuration
	@EnableWebMvc
	@ComponentScan("com.aaron.springweb.web.controller")
	public static class WebAppConfig {
	}

	@Configuration
	@EnableTransactionManagement
	@ComponentScan("com.aaron.springweb.core")
	public static class RootConfig {
		@Bean
		public DataSource dataSource() {
			DataSource bean = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
					.addScript("classpath:schema.sql").build();
			return bean;
		}
	}
	 */
}
