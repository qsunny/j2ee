package com.aaron.shiro.showcase5.service;

import com.aaron.shiro.showcase5.entity.Permission;

/**
 * Created by Administrator on 2016/12/27.
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
