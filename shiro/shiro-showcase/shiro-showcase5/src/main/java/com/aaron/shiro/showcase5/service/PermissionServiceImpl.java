package com.aaron.shiro.showcase5.service;

import com.aaron.shiro.showcase5.dao.PermissionDao;
import com.aaron.shiro.showcase5.dao.PermissionDaoImpl;
import com.aaron.shiro.showcase5.entity.Permission;

/**
 * Created by Administrator on 2016/12/27.
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
