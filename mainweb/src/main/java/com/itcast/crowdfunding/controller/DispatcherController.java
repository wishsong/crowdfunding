package com.itcast.crowdfunding.controller;


import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.exception.DefineException;
import com.itcast.crowdfunding.manager.service.UserService;
import com.itcast.crowdfunding.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

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

    @RequestMapping("/doLogin")
    public String doLogin(User user, HttpSession session) throws DefineException {

        User userRes = userService.login(user);
        if(userRes==null){
            throw new DefineException("用户名或密码错误！");
        }

        session.setAttribute(Const.LOGIN_USER,user);

        return "redirect:/main.htm";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

}
