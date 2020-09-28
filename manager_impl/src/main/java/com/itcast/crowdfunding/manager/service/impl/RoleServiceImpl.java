package com.itcast.crowdfunding.manager.service.impl;

import com.itcast.crowdfunding.bean.Role;
import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.manager.dao.RoleMapper;
import com.itcast.crowdfunding.manager.dao.UserMapper;
import com.itcast.crowdfunding.manager.service.RoleService;
import com.itcast.crowdfunding.util.Page;
import com.itcast.crowdfunding.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page queryList(Map<String, Object> paramMap) {
        List<Role> roleList =  roleMapper.queryList(paramMap);
        int totalsize = roleMapper.queryAllNum(paramMap);
        Page pageRes = (Page)paramMap.get("page");
        pageRes.setDatas(roleList);
        pageRes.setTotalsize(totalsize);
        return pageRes;

    }

    @Override
    public int assignPermission(int roleid, Data datas) {
        roleMapper.deleteRolePermission(roleid);
        int res = 0;
        Map<String,Integer>[] map = new HashMap[datas.getIds().size()];

        for(int i=0;i<datas.getIds().size();i++){
            map[i] = new HashMap<>();
            map[i].put("roleid",roleid);
            map[i].put("permissionid",datas.getIds().get(i));
            res += roleMapper.insert(map[i]);
        }

//        Map<String,Object> map = new HashMap<>();
//        map.put("roleid",roleid);
//        map.put("ids",datas.getIds());


//        System.out.println("=========================");
//        System.out.println("删除成功");
//        System.out.println("=========================");
//        int res = roleMapper.insert(map);
//        System.out.println("=========================");
        return res;
    }
}
