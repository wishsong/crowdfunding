package com.itcast.crowdfunding.manager.controller;


import com.itcast.crowdfunding.manager.service.UserService;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Page;
import com.itcast.crowdfunding.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "user/index";
    }

    @ResponseBody
    @RequestMapping("/doIndex")
    public Object doIndex(int pageno,int pagesize,String queryText,String buttonType){

        AjaxResult result = new AjaxResult();
        try {
            Map<String,Object> paramMap  = new HashMap<String,Object>();
            Page page = new Page(pageno,pagesize);
            Page pageRes;

            paramMap.put("page",page);
            if(StringUtil.isNotEmpty(buttonType)){
                paramMap.put("buttonType",buttonType);
                queryText = "%"+queryText+"%";
            }
            if(StringUtil.isNotEmpty(queryText)){
                paramMap.put("queryText",queryText);
            }

            pageRes = userService.queryList_1(paramMap);
            result.setPage(pageRes);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("加载失败！");
            e.printStackTrace();
        }
        return result;
    }

//    @ResponseBody
//    @RequestMapping("/doIndex")
//    public Object doIndex(int pageno,int pagesize,String queryText,String buttonType){
//
//        AjaxResult result = new AjaxResult();
//        try {
//            Page page = new Page(pageno,pagesize);
//            Page pageRes;
//
//            if(StringUtil.isEmpty(queryText)){
//                pageRes = userService.queryList(page);
//            }else{
//                pageRes = userService.queryListByQueryText(page,queryText,buttonType);
//            }
//
//            result.setPage(pageRes);
//            result.setSuccess(true);
//        } catch (Exception e) {
//            result.setSuccess(false);
//            result.setMessage("加载失败！");
//            e.printStackTrace();
//        }
//        return result;
//    }
}
