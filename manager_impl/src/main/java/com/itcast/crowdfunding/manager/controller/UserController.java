package com.itcast.crowdfunding.manager.controller;


import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.manager.service.UserService;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Page;
import com.itcast.crowdfunding.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/update")
    public String update(String id, HttpServletRequest request){

        User userRes = userService.queryById(Integer.parseInt(id));
        request.setAttribute("user",userRes);
        return "user/update";
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

    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(User user){
        AjaxResult result = new AjaxResult();

        //服务器端的校验，防止在地址栏用同步方式手动输入空参数来保存用户信息
        if(StringUtil.isEmpty(user.getUsername())||StringUtil.isEmpty(user.getLoginacct())||StringUtil.isEmpty(user.getEmail())){
            result.setSuccess(false);
            result.setMessage("信息不能为空！");
            return result;
        }
        try {
            int res = userService.saveUser(user);
            result.setSuccess(res==1);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("保存失败！");
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(User user){
        AjaxResult result = new AjaxResult();

        //服务器端的校验，防止在地址栏用同步方式手动输入空参数来保存用户信息
        if(StringUtil.isEmpty(user.getUsername())||StringUtil.isEmpty(user.getLoginacct())||StringUtil.isEmpty(user.getEmail())){
            result.setSuccess(false);
            result.setMessage("信息不能为空！");
            return result;
        }
        try {
            int res = userService.updateUser(user);
            result.setSuccess(res==1);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("保存失败！");
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
