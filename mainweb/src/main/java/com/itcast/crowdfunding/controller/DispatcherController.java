package com.itcast.crowdfunding.controller;


import com.itcast.crowdfunding.bean.Permission;
import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.exception.DefineException;
import com.itcast.crowdfunding.manager.service.UserService;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Const;
import com.itcast.crowdfunding.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

//    @RequestMapping("/doLogin")
//    public String doLogin(String loginacct,String  userpswd,String type, HttpSession session) throws DefineException {
//
//        Map<String,Object> userMap = new HashMap<>();
//            userMap.put("loginacct",loginacct);
//            userMap.put("userpswd",userpswd);
//            userMap.put("type",type);
//
//            User userRes = userService.login(userMap);
//        if(userRes==null){
//            throw new DefineException("用户名或密码错误！");
//        }
//
//        session.setAttribute(Const.LOGIN_USER,userRes);
//
//        return "redirect:/main.htm";
//    }



    @ResponseBody
    @RequestMapping("/doLogin")
    public Object doLogin(String loginacct, String  userpswd, String type, HttpSession session, HttpServletRequest request)  {



        AjaxResult result = new AjaxResult();

        try {
            Map<String,Object> userMap = new HashMap<>();
            userMap.put("loginacct",loginacct);
            userMap.put("userpswd", MD5Util.digest(userpswd));
            userMap.put("type",type);

            User userRes = userService.login(userMap);
            session.setAttribute(Const.LOGIN_USER,userRes);
            result.setSuccess(true);
        } catch (DefineException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/main")
    public String main(HttpSession session){

        Permission permissionRoot = new Permission();
        List<Permission> permissions = new ArrayList<Permission>();
        permissions = userService.queryPermissionByUser((User)session.getAttribute(Const.LOGIN_USER));
        Set<String> myURIs = new HashSet<String>();
        for(Permission permission:permissions){
            myURIs.add("/"+permission.getUrl());
        }
        session.setAttribute(Const.MY_URIS,myURIs);

        Map<Integer,Permission> permissionMap = new HashMap<>();
        for(Permission permission:permissions){
            permissionMap.put(permission.getId(),permission);
        }

        for(Permission permission:permissions){
            if(permission.getPid()==null){
                permissionRoot = permission;
            }else{
                Permission parent = permissionMap.get(permission.getPid());
                parent.getChildren().add(permission);
            }
        }

        session.setAttribute("permissionRoot",permissionRoot);
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        System.out.println(session.getAttribute("user"));
        session.invalidate();
        return "redirect:/index.htm";

    }

}
