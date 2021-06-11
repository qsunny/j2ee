package com.aaron.springbootDemoMp.core.service.user.impl;

import com.aaron.base.utils.CommUtils;
import com.aaron.beans.springbootDemoMp.user.User;
import com.aaron.form.springbootDemoMp.UserQueryForm;
import com.aaron.springbootDemoMp.core.service.user.IUserService;
import com.aaron.springbootDemoMp.dao.user.IUserDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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

    @Resource
    private IUserDao userDao;

    @Override
    public Page<User> queryUserPage(@NonNull UserQueryForm queryForm) throws Exception {
        Page<User> page = new Page<>(queryForm.getPage(),queryForm.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(CommUtils.isNotEmpty(queryForm.getName()),"name",queryForm.getName())
        .like(CommUtils.isNotEmpty(queryForm.getName()),"email",queryForm.getName())
                .between((!Objects.isNull(queryForm.getStartTime())&&!Objects.isNull(queryForm.getEndTime()))
                        ,"create_time",queryForm.getStartTime(),queryForm.getEndTime())
                .ge(CommUtils.toInt(queryForm.getAge())>0,"age",queryForm.getAge());

        page = this.page(page,queryWrapper);
        return page;
    }
}
