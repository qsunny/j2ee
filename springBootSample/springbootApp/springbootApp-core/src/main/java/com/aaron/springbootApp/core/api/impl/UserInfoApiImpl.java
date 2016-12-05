package com.aaron.springbootApp.core.api.impl;

import com.aaron.springbootApp.api.IUserInfoApi;
import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.constant.SpringbootAppConstant;
import com.aaron.springbootApp.core.service.IUserService;
import com.aaron.tools.vo.ReturnVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aaron.qiu on 2016/12/5.
 */
@Service
public class UserInfoApiImpl implements IUserInfoApi{
    private static final Logger log = LoggerFactory.getLogger(UserInfoApiImpl.class);

    @Autowired
    private IUserService userService;

    @Override
    public ReturnVo<User> getUserId(String Id) {
        ReturnVo<User> retVo = new ReturnVo<User>();
        retVo.setResponseCode(SpringbootAppConstant.RESPONSE_SUCCESS_CODE);
        retVo.setResponseMsg("查询成功");
        if (StringUtils.isBlank(Id)) {
            retVo.setResponseCode(SpringbootAppConstant.RESPONSE_FAILTER_CODE);
            retVo.setResponseMsg("用户id不能为空!");
            return retVo;
        }

        try {
            User u = userService.getUserById(Id);
            if (u == null) {
                retVo.setResponseCode(SpringbootAppConstant.RESPONSE_FAILTER_CODE);
                retVo.setResponseMsg("用户信息为空!");
                return retVo;
            } else {
                retVo.setVo(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getUserId",e);
            retVo.setResponseCode(SpringbootAppConstant.RESPONSE_FAILTER_CODE);
            retVo.setResponseMsg("查询用户出错!");
        }

        return retVo;
    }
}
