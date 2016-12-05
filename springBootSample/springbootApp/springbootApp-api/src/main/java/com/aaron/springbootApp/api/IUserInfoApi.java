package com.aaron.springbootApp.api;

import com.aaron.springbootApp.bean.User;
import com.aaron.tools.vo.ReturnVo;

/**
 * Created by aaron.qiu on 2016/12/5.
 */
public interface IUserInfoApi {

    /**
     * 通过用户id获取用户信息
     *
     * @param Id 用户id
     * @return
     */
    public ReturnVo<User> getUserId(String Id);

}
