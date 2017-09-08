package com.aaron.springbootApp.web.controller;

import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.core.service.IUserService;
import com.aaron.springbootApp.core.service.RedisService;
import com.aaron.springbootApp.exception.SpringbootAppException;
import com.aaron.springbootApp.vcode.Captcha;
import com.aaron.springbootApp.vcode.GifCaptcha;
import com.aaron.tools.utils.JsonUtils;
import com.aaron.tools.utils.UUIDGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试
 * @author aaron.qiu
 */
@Controller
public class UserLoginController {

	private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);
	
	@Resource
	private IUserService userService;
	
	@Resource
	private RedisService redisService;

	@RequestMapping("/login")
	public String index(Map<String,Object> model) {
		return "login";
	}


	/**
	 * ajax登录请求
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitLogin(String username, String password,String vcode,Boolean rememberMe,Model model) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

		if(vcode==null||vcode==""){
			resultMap.put("status", 500);
			resultMap.put("message", "验证码不能为空！");
			return resultMap;
		}

		Session session = SecurityUtils.getSubject().getSession();
		//转化成小写字母
		vcode = vcode.toLowerCase();
		String v = (String) session.getAttribute("_code");
		//还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
		//session.removeAttribute("_come");
		if(!vcode.equals(v)){
			resultMap.put("status", 500);
			resultMap.put("message", "验证码错误！");
			return resultMap;
		}

		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
			SecurityUtils.getSubject().login(token);
			resultMap.put("status", 200);
			resultMap.put("message", "登录成功");

		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="logout",method =RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> logout(){
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			//退出
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="getGifCode",method= RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");
			/**
			 * gif格式动画验证码
			 * 宽，高，位数。
			 */
			Captcha captcha = new GifCaptcha(146,33,4);
			//输出
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession(true);
			//存入Session
			session.setAttribute("_code",captcha.text().toLowerCase());
		} catch (Exception e) {
			System.err.println("获取验证码异常："+e.getMessage());
		}
	}

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
