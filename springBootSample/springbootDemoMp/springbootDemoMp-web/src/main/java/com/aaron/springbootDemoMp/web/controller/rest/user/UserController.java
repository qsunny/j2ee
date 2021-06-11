package com.aaron.springbootDemoMp.web.controller.rest.user;


import com.aaron.base.vo.ReturnVo;
import com.aaron.beans.springbootDemoMp.user.User;
import com.aaron.form.springbootDemoMp.UserQueryForm;
import com.aaron.springbootDemoMp.core.service.user.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.aaron.springbootDemoMp.web.controller.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 *  用户前端控制器
 * </p>
 *
 * @author Aaron.Qiu
 * @since 2021-06-07
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private IUserService userService;

    @RequestMapping("/queryUserPage")
    public ReturnVo<Page<User>> queryUserPage(@RequestBody UserQueryForm queryForm) throws Exception {
        return new ReturnVo<>(userService.queryUserPage(queryForm));
    }
}
