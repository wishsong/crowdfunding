package com.itcast.crowdfunding.manager.service.impl;

import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.exception.DefineException;
import com.itcast.crowdfunding.manager.dao.UserMapper;
import com.itcast.crowdfunding.manager.service.UserService;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Page;
import com.itcast.crowdfunding.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

//    @Override
//    public Page queryList(Page page) {
//
//        List<User> userList =  userMapper.queryList(page.getStartIndex(),page.getPagesize());
//        int totalsize = userMapper.queryAllNum();
//        Page pageRes = page;
//        pageRes.setDatas(userList);
//        pageRes.setTotalsize(totalsize);
//        return pageRes;
//    }
//
//    @Override
//    public Page queryListByQueryText(Page page,String queryText,String buttonType) {
//        String queryText_temp = queryText;
//        String status = "";
//        if(StringUtil.isNotEmpty(buttonType)){
//            queryText_temp = "%"+queryText+"%";
//            status = "vague";
//        }
//        List<User> userList =  userMapper.queryListByQueryText(page.getStartIndex(),page.getPagesize(),queryText_temp,status);
//        int totalsize = userMapper.queryAllNumByQueryText(queryText_temp,status);
//        Page pageRes = page;
//        pageRes.setDatas(userList);
//        pageRes.setTotalsize(totalsize);
//        return pageRes;
//    }



    @Override
    public Page queryList_1(Map<String,Object> paramMap) {

        List<User> userList =  userMapper.queryList_1(paramMap);
        int totalsize = userMapper.queryAllNum_1(paramMap);
        Page pageRes = (Page)paramMap.get("page");
        pageRes.setDatas(userList);
        pageRes.setTotalsize(totalsize);
        return pageRes;
    }
}
