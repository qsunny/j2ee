package com.example.springboot.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.service.HelloWorldService;

//@RestController 
@Controller
public class SampleController {
	
	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@Autowired
	private HelloWorldService helloWorldService;
	
	@ResponseBody
	@RequestMapping("/")
	public String helloWorld() {
		return this.helloWorldService.getHelloMessage();
	}
	
	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}
	
	
	
	

}