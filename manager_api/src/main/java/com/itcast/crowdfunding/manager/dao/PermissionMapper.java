package com.itcast.crowdfunding.manager.dao;

import com.itcast.crowdfunding.bean.Permission;

import java.util.List;

public interface PermissionMapper {
    Permission queryRootPermission();

    List<Permission> queryChildrenById(Integer id);

    List<Permission> queryAllPermission();

    int addPermission(Permission permission);

    int deletePermission(int parseInt);

    int updatePermission(Permission permission);

    Permission queryPermissionById(int id);

    List<Integer> queryPermissionidsByRoleid(Integer roleid);
}
