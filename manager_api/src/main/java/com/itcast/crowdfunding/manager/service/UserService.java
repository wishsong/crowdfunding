package com.itcast.crowdfunding.manager.service;

import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.exception.DefineException;

import java.util.Map;

public interface UserService {
    User login(Map userMap) throws DefineException;
}
