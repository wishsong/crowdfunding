package com.itcast.crowdfunding.manager.dao;

import com.itcast.crowdfunding.bean.Role;
import com.itcast.crowdfunding.util.Page;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    List<Role> queryList(Map<String, Object> paramMap);

    int queryAllNum(Map<String, Object> paramMap);

    void deleteRolePermission(int roleid);

    int insert(Map map);
}
