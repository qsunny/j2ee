package com.example.springboot.web;

import com.example.springboot.bean.User;
import com.example.springboot.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

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

	@ResponseBody
	@RequestMapping("/getUser")
	public User getUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("receive param test===="+request.getParameter("test"));
		System.out.println("receive param test2===="+request.getParameter("test2"));
		User u = new User();
		u.setAge(23);
		u.setFavorite(new String[]{"basketball","football","music"});
		u.setId(232323);
		u.setName("aaron.qiu");
		return u;
	}
	
	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		System.out.println("===========dddddddddddd");
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}
	
	
	
	

}