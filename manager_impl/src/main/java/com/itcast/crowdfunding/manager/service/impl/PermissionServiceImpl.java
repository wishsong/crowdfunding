package com.itcast.crowdfunding.manager.service.impl;

import com.itcast.crowdfunding.bean.Permission;
import com.itcast.crowdfunding.manager.dao.PermissionMapper;
import com.itcast.crowdfunding.manager.dao.UserMapper;
import com.itcast.crowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("permissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public Permission queryRootPermission() {
        return permissionMapper.queryRootPermission();
    }

    @Override
    public List<Permission> queryChildrenById(Integer id) {
        return permissionMapper.queryChildrenById(id);
    }

    @Override
    public List<Permission> queryAllPermission() {
        return permissionMapper.queryAllPermission();
    }
}
