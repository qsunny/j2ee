package com.aaron.waterspringboot.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/waterspringboot")
public class IndexController {
	
	private final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Value("${app.name}")
	private String applicationName;
	
	@Autowired
	private Environment env;

	
	@RequestMapping("/index")
	public String index(Map<String, Object> model) {

		log.debug("index() is executed!");
		//env.getProperty("jdbc.name");
		model.put("title", applicationName);
		model.put("msg", "Hello Gradle!");
		
		return "index";
	}
}
