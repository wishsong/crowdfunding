package com.itcast.crowdfunding.manager.controller;


import com.itcast.crowdfunding.bean.Role;
import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.manager.service.UserService;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Page;
import com.itcast.crowdfunding.util.StringUtil;
import com.itcast.crowdfunding.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public String update(String id, Map map){

        User userRes = userService.queryById(Integer.parseInt(id));
        map.put("user", userRes);
        return "user/update";
    }


    @RequestMapping("/assignRole")
    public String assignRole(String id,Map map){

        List<Role> allRole = new ArrayList<Role>();
        List<Integer> assignedRole = new ArrayList<Integer>();

        List<Role> leftRoleList = new ArrayList<Role>();
        List<Role> rightRoleList = new ArrayList<Role>();

        allRole = userService.queryAllRole();
        assignedRole = userService.queryAssignedRole(Integer.parseInt(id));

        for(Role role:allRole){
            if(assignedRole.contains(role.getId())){
                rightRoleList.add(role);
            }else{
                leftRoleList.add(role);
            }
        }

        map.put("leftRoleList",leftRoleList);
        map.put("rightRoleList",rightRoleList);


        return "user/assignRole";
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

    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(String id){
        AjaxResult result = new AjaxResult();

        try {
            int res = userService.deleteUser(id);
            result.setSuccess(res==1);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！");
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/doDeletes")
    public Object doDeletes(String[] ids){
        AjaxResult result = new AjaxResult();

        try {
            int res = 0;
            for(String id:ids){
                System.out.println(id);
               res += userService.deleteUser(id);
                result.setSuccess(res==ids.length);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("删除失败！");
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/doAssignRole")
    public Object doAssignRole(String userid, Data data){
        AjaxResult result = new AjaxResult();
        try {
            int res = userService.assignRole(userid,data);
            result.setSuccess(res>=0);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("分配角色失败！");
            e.printStackTrace();
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/doUnAssignRole")
    public Object doUnAssignRole(String userid, Data data){
        AjaxResult result = new AjaxResult();
        try {
            int res = userService.assignRole(userid,data);
            result.setSuccess(res==data.getIds().size());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("取消角色失败！");
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
