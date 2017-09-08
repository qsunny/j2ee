package com.aaron.springbootApp.core.shiro;

import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.core.service.IUserService;
import com.aaron.springbootApp.exception.SpringbootAppException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:用户信息相关操作service接口
 * @Author:Aaron.Qiu
 * @Since:2017-08-04
 * @Copyright:Copyright (c)  2017 ~ 2018 版权所有
 */
public class UserRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private IUserService userService;

    //用户登录次数计数  redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     * @param authcToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        //访问一次，计数一次
        //ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        //opsForValue.increment(SHIRO_LOGIN_COUNT+name, 1);
        //计数大于5时，设置用户被锁定一小时
        /*
        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+name))>=5){
            opsForValue.set(SHIRO_IS_LOCK+name, "LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK+name, 1, TimeUnit.HOURS);
        }
        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+name))){
            throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
        }
        */
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        List<User> userList = null;
        try {
            userList = userService.getAll(user);
        } catch (SpringbootAppException e) {
            e.printStackTrace();
            throw new AuthenticationException("认证用户异常");
        }

        User u = null;
        if(userList.size()!=0){
            u = userList.get(0);
        }
        if (null == u) {
            throw new AccountException("帐号或密码不正确！");
       // }else if("0".equals(user.getStatus())){
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
           // throw new DisabledAccountException("此帐号已经设置为禁止登录！");
        }else{
            //登录成功
            //更新登录时间 last login time
//            user.setLastLoginTime(new Date());
//            sysUserService.updateById(user);
            //清空登录计数
//            opsForValue.set(SHIRO_LOGIN_COUNT+name, "0");
        }
        logger.info("身份认证成功，登录用户："+name);
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        /*
        System.out.println("权限认证方法：MyShiroRealm.doGetAuthorizationInfo()");
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String userId = user.getId();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        */
        //根据用户ID查询角色（role），放入到Authorization里。
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		List<SysRole> roleList = sysRoleService.selectByMap(map);
		Set<String> roleSet = new HashSet<String>();
		for(SysRole role : roleList){
			roleSet.add(role.getType());
		}*/
        /*
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        info.setRoles(roleSet);
        */
        //根据用户ID查询权限（permission），放入到Authorization里。
		/*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
		Set<String> permissionSet = new HashSet<String>();
		for(SysPermission Permission : permissionList){
			permissionSet.add(Permission.getName());
		}*/
        /*
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);
        return info;
        */
        return null;
    }
}
