package com.itcast.crowdfunding.manager.controller;


import com.itcast.crowdfunding.manager.service.UserService;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Object doIndex(int pageno,int pagesize){
        System.out.println("controller");
        AjaxResult result = new AjaxResult();
        try {
            System.out.println("controller查询成功");
            Page page = new Page(pageno,pagesize);

            Page pageRes = userService.queryList(page);
            result.setPage(pageRes);
            result.setSuccess(true);
            System.out.println(pageRes);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("controller查询失败");
            result.setSuccess(false);
            result.setMessage("加载失败！");
            e.printStackTrace();
        }
        return result;
    }
}
