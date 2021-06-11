package com.aaron.springbootDemoMp.core.service.user.impl;

import com.aaron.beans.springbootDemoMp.user.User;
import com.aaron.springbootDemoMp.core.service.user.IUserService;
import com.aaron.springbootDemoMp.dao.user.IUserDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aaron.Qiu
 * @since 2021-06-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserDao, User> implements IUserService {

}
