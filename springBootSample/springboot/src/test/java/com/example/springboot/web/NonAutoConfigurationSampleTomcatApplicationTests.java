package com.example.springboot.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.springboot.Application;
import com.example.springboot.service.HelloWorldService;
import com.example.springboot.web.NonAutoConfigurationSampleTomcatApplicationTests.NonAutoConfigurationSampleTomcatApplication;

/**
 * Basic integration tests for demo application.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(NonAutoConfigurationSampleTomcatApplication.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class NonAutoConfigurationSampleTomcatApplicationTests {

	@Value("${local.server.port}")
	private int port;

	@Configuration
	@Import({ EmbeddedServletContainerAutoConfiguration.class,
			DispatcherServletAutoConfiguration.class,
			ServerPropertiesAutoConfiguration.class, WebMvcAutoConfiguration.class,
			HttpMessageConvertersAutoConfiguration.class,
			PropertyPlaceholderAutoConfiguration.class })
	@ComponentScan(basePackageClasses = { SampleController.class,
			HelloWorldService.class })
	public static class NonAutoConfigurationSampleTomcatApplication {

		public static void main(String[] args) throws Exception {
			SpringApplication.run(Application.class, args);
		}

	}

	@Test
	public void testHome() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate()
				.getForEntity("http://localhost:" + this.port, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("Hello World", entity.getBody());
	}

}
