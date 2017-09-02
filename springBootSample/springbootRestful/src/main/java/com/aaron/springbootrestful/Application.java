package com.aaron.springbootrestful;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * http://localhost:8080/swagger-ui.html
 */
//@SpringBootApplication(scanBasePackages = {"com.aaron.springbootrestful.config.*"})
@SpringBootApplication
public class Application {
	
//	@RequestMapping("/")
//    @ResponseBody
//    String home() {
//        return "Hello World!";
//    }
	private static final Log logger = LogFactory.getLog(Application.class);

	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {

			@Override
			public void contextInitialized(ServletContextEvent sce) {
				logger.info("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				logger.info("ServletContext destroyed");
			}

		};
	}
	
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	
}
