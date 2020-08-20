package com.itcast.crowdfunding.manager.controller;


import com.itcast.crowdfunding.bean.Permission;
import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.manager.service.PermissionService;
import com.itcast.crowdfunding.manager.service.UserService;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        System.out.println("==================================");
        return "permission/index";
    }

    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
        AjaxResult result = new AjaxResult();
//        List<Permission> list  = new ArrayList<Permission>();
//
//        Permission root  =  permissionService.queryRootPermission();
//
//        queryChildren(root);//使用递归简化代码，同时完成所有深度树的查询
//
//        list.add(root);
//        result.setData(list);
//        result.setSuccess(true);

        List<Permission> resList  = new ArrayList<Permission>();
        List<Permission> list  = new ArrayList<Permission>();
        list = permissionService.queryAllPermission();

        Permission root = null;
        for(Permission permission:list){
            if(permission.getPid()==null){
                root = permission;
                break;
            }
        }


        queryChildrenByParent(root,list);
        resList.add(root);

        result.setData(resList);
        result.setSuccess(true);
        return result;
    }

//    private void queryChildren(Permission permission){
//        List<Permission> children = permissionService.queryChildrenById(permission.getId());
//        permission.setChildren(children);
//        permission.setOpen(true);
//
//        for(Permission child:children){
//            queryChildren(child);
//        }
//    }

    private void queryChildrenByParent(Permission permission,List<Permission> list){

        for(Permission per:list){
            if(permission.getId() == per.getPid()){
                permission.getChildren().add(per);
                queryChildrenByParent(per,list);
            }
        }
    }

}
