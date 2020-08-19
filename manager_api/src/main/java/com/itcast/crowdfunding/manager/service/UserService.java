package com.itcast.crowdfunding.manager.service;

import com.itcast.crowdfunding.bean.Role;
import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.exception.DefineException;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Page;
import com.itcast.crowdfunding.vo.Data;

import java.util.List;
import java.util.Map;

public interface UserService {
    User login(Map userMap) throws DefineException;

    public Page queryList_1(Map<String,Object> paramMap);

    int saveUser(User user);

    public User queryById(int id);

    int updateUser(User user);

    int deleteUser(String id);

    List<Role> queryAllRole();

    List<Integer> queryAssignedRole(int id);

    int assignRole(String userid, Data data);
    int unassignRole(String userid, Data data);
//    Page queryList(Page page);
//
//    Page queryListByQueryText(Page page,String queryText,String buttonType);


}
