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

    @Override
    public int addPermission(Permission permission) {
        return permissionMapper.addPermission(permission);
    }

    @Override
    public int deletePermission(String id) {
        return permissionMapper.deletePermission(Integer.parseInt(id));
    }

    @Override
    public int updatePermission(Permission permission) {
        return permissionMapper.updatePermission(permission);
    }

    @Override
    public Permission queryPermissionById(int id) {
        return permissionMapper.queryPermissionById(id);
    }

    @Override
    public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
        return permissionMapper.queryPermissionidsByRoleid(roleid);
    }
}
