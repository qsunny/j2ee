package com.aaron.springbootDemoMp.core.service.user;

import com.aaron.beans.springbootDemoMp.user.User;
import com.aaron.form.springbootDemoMp.UserQueryForm;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  用户服务类
 * </p>
 * @author Aaron.Qiu
 * @since 2021-06-07
 */
public interface IUserService extends IService<User> {

    /**
     * 分页查询用户
     * @param queryForm
     * @return
     * @throws Exception
     */
    Page<User> queryUserPage(UserQueryForm queryForm) throws Exception;

}
