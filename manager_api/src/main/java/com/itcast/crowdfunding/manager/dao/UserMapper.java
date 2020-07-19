package com.itcast.crowdfunding.manager.dao;

import com.itcast.crowdfunding.bean.Permission;
import com.itcast.crowdfunding.bean.Role;
import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Page;
import com.itcast.crowdfunding.vo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	User queryUserlogin(Map<String, Object> paramMap);

//	List<User> queryList(@Param("startIndex") Integer startIndex,@Param("pagesize") Integer pagesize);
//
//	int queryAllNum();
//
//	List<User> queryListByQueryText(@Param("startIndex") Integer startIndex,@Param("pagesize") Integer pagesize,@Param("queryText") String queryText,@Param("status") String status);
//
//	int queryAllNumByQueryText(@Param("queryText") String queryText,@Param("status") String status);

	List<User> queryList_1(Map<String, Object> paramMap);

	int queryAllNum_1(Map<String, Object> paramMap);

	int insert(User user);

    User queryById(int id);

	int update(User user);

    int deleteById(int id);
}