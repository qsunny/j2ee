package com.aaron.shiro.showcase5.dao;

import com.aaron.shiro.showcase5.entity.Role;

/**
 * Created by Administrator on 2016/12/27.
 */
public interface RoleDao {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
