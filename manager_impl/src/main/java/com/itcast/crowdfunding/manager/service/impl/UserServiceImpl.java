package com.itcast.crowdfunding.manager.service.impl;

import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.exception.DefineException;
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
    public User login(Map userMap) throws DefineException {

        User userRes = userMapper.queryUserlogin(userMap);
        if(userRes==null){
            throw new DefineException("用户名或密码错误!");
        }
        return userRes;
    }
}
