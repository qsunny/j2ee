package com.aaron.springbootApp.web.controller;

import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.core.service.IUserService;
import com.aaron.springbootApp.core.service.RedisService;
import com.aaron.springbootApp.exception.SpringbootAppException;
import com.aaron.tools.utils.JsonUtils;
import com.aaron.tools.utils.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/userlogin")
public class UserLoginController {

	private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);
	
	@Resource
	private IUserService userService;
	
	@Resource
	private RedisService redisService;
	
	@RequestMapping("/index")
	public String loginIndex(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) {
		
		try {
			List<User> list;
			list = userService.getAll(user);
			model.addAttribute("userList", list);
			
			String uString = redisService.get(user.getId());
			model.addAttribute("uString", uString);

			User u = (User) JsonUtils.jsonToObj(uString, User.class);
			System.out.println("redis user ==========="+u);
			model.addAttribute("user", u);
		} catch (SpringbootAppException e) {
			log.error("loginIndex",e);
		}
		return "hello";
	}
	
	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request,HttpServletResponse response,ModelMap model,User u) {
		try {			
			u.setId(UUIDGenerator.generate());
			userService.insertUser(u);
		} catch (SpringbootAppException e) {
			log.error(e.getMessage());
		}
		
		return "redirect:/userlogin/index.jhtml";
	}
	
	
	@RequestMapping("/updateUser")
	public String updateUser(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) {
		
		try {
			userService.updateUser(user);
		} catch (SpringbootAppException e) {
			log.error(e.getMessage());
		}
		return "redirect:/userlogin/index.jhtml";
	}
	
	@RequestMapping("/delUser")
	public String delUser(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) {
		try {
			userService.deleteUserById(user.getId());
		} catch (SpringbootAppException e) {
			log.error(e.getMessage());
		}
		return "redirect:/userlogin/index.jhtml";
	}
	
	@RequestMapping("/redisAdd")
	public String redisAdd(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) throws SpringbootAppException {
		User u = userService.getUserById(user.getId());
		redisService.put(user.getId(), JsonUtils.toJson(u));
		return "redirect:/userlogin/index.jhtml?id="+u.getId();
	}


}
