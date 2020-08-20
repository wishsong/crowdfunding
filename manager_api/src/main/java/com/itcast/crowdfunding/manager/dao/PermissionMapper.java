package com.itcast.crowdfunding.manager.dao;

import com.itcast.crowdfunding.bean.Permission;

import java.util.List;

public interface PermissionMapper {
    Permission queryRootPermission();

    List<Permission> queryChildrenById(Integer id);

    List<Permission> queryAllPermission();
}
