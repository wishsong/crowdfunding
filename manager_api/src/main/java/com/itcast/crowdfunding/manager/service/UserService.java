package com.itcast.crowdfunding.manager.service;

import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.exception.DefineException;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Page;

import java.util.List;
import java.util.Map;

public interface UserService {
    User login(Map userMap) throws DefineException;
    Page queryList(Page page);
}
