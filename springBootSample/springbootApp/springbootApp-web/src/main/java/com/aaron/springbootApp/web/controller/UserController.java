package com.aaron.springbootApp.web.controller;

import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.core.service.IUserService;
import com.aaron.springbootApp.exception.SpringbootAppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron.qiu on 2016/12/3.
 */
@RestController
@RequestMapping(value="/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<User>();
        try {
            r = userService.getAll(null);
        } catch (SpringbootAppException e) {
            log.error("getUserList",e);
        }
        return r;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        try {
            int flag = userService.insertUser(user);
            return flag==1 ? "success" : "fail";
        } catch (SpringbootAppException e) {
            log.error("postUser",e);
        }
        return "fail";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable String id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        try {
            User u = userService.getUserById(id);
            return u;
        } catch (SpringbootAppException e) {
            log.error("getUser",e);
        }
        return null;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String putUser(@PathVariable String id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        user.setId(id);
        try {
            int flag = userService.updateUser(user);
            return flag==1 ? "success" : "fail";
        } catch (SpringbootAppException e) {
            log.error("putUser",e);
        }
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        try {
            int flag = userService.deleteUserById(id);
            return flag==1 ? "success" : "fail";
        } catch (SpringbootAppException e) {
            log.error("deleteUser",e);
        }
        return "success";
    }

}
