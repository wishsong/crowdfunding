package com.itcast.crowdfunding.manager.dao;

import com.itcast.crowdfunding.bean.Permission;
import com.itcast.crowdfunding.bean.Role;
import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.vo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	User queryUserlogin(Map<String, Object> paramMap);

}