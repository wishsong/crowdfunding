package com.itcast.crowdfunding.manager.service;

import com.itcast.crowdfunding.util.Page;
import com.itcast.crowdfunding.vo.Data;

import java.util.Map;

public interface RoleService {
    Page queryList(Map<String, Object> paramMap);

    int assignPermission(int roleid, Data datas);
}
