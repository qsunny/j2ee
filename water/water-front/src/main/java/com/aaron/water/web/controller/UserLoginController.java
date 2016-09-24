package com.aaron.water.web.controller;

import com.aaron.tools.utils.JsonUtils;
import com.aaron.tools.utils.UUIDGenerator;
import com.aaron.water.bean.User;
import com.aaron.water.core.service.IUserService;
import com.aaron.water.core.service.RedisService;
import com.aaron.water.exception.WaterException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 测试
 * @author aaron.qiu
 */
@Controller
@RequestMapping("/water/userlogin")
public class UserLoginController {

	private static final Logger log = Logger.getLogger(UserLoginController.class);
	
	@Resource
	private IUserService userService;
	
	@Resource
	private RedisService redisService;
	
	@RequestMapping("/index")
	public String loginIndex(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) {
		
		try {
			List<User> list;
			list = userService.getAll(user);
			model.addAttribute("list", list);
			
			String uString = redisService.get(user.getId());
			model.addAttribute("uString", uString);
			
			User u = (User) JsonUtils.jsonToObj(uString, User.class);
			model.addAttribute("user", u);
		} catch (WaterException e) {
			log.error(e);
		}
		return "/hello";
	}
	
	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request,HttpServletResponse response,ModelMap model,User u) {
		try {			
			u.setId(UUIDGenerator.generate());
			userService.insertUser(u);
		} catch (WaterException e) {
			log.error(e);
		}
		
		return "redirect:/water/userlogin/index.jhtml";
	}
	
	
	@RequestMapping("/updateUser")
	public String updateUser(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) {
		
		try {
			userService.updateUser(user);
		} catch (WaterException e) {
			log.error(e);
		}
		return "redirect:/water/userlogin/index.jhtml";
	}
	
	@RequestMapping("/delUser")
	public String delUser(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) {
		try {
			userService.deleteUserById(user.getId());
		} catch (WaterException e) {
			log.error(e);
		}
		return "redirect:/water/userlogin/index.jhtml";
	}
	
	@RequestMapping("/redisAdd")
	public String redisAdd(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) {
		String userId = UUIDGenerator.generate();
		redisService.put(userId, JsonUtils.toJson(user));
		return "redirect:/water/userlogin/index.jhtml?id="+userId;
	}
	
	
}
