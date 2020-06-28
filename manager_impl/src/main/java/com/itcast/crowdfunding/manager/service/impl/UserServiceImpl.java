package com.itcast.crowdfunding.manager.service.impl;

import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.manager.dao.UserMapper;
import com.itcast.crowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user){
        Map<String,Object> userMap = new HashMap<String,Object>();
        userMap.put("loginacct",user.getLoginacct());
        userMap.put("userpswd",user.getUserpswd());

        User userRes = userMapper.queryUserlogin(userMap);
        return userRes;
    }
}
