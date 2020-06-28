package com.itcast.crowdfunding.manager.service.impl;

import com.itcast.crowdfunding.manager.dao.TestDao;
import com.itcast.crowdfunding.manager.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    public void insert() {
        System.out.println("service类中的方法");
        Map map = new HashMap();
        map.put("name","1111");
        testDao.insert(map);
    }
}
