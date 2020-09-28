package com.itcast.crowdfunding.manager.service;

import com.itcast.crowdfunding.bean.Permission;

import java.util.List;

public interface PermissionService {
    Permission queryRootPermission();

    List<Permission> queryChildrenById(Integer id);

    List<Permission> queryAllPermission();

    int addPermission(Permission permission);

    int deletePermission(String id);

    int updatePermission(Permission permission);

    Permission queryPermissionById(int id);

    List<Integer> queryPermissionidsByRoleid(Integer roleid);
}
