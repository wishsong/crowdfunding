package com.itcast.crowdfunding.manager.controller;


import com.itcast.crowdfunding.bean.Permission;
import com.itcast.crowdfunding.manager.service.PermissionService;
import com.itcast.crowdfunding.manager.service.RoleService;
import com.itcast.crowdfunding.manager.service.UserService;
import com.itcast.crowdfunding.util.AjaxResult;
import com.itcast.crowdfunding.util.Page;
import com.itcast.crowdfunding.util.StringUtil;
import com.itcast.crowdfunding.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "role/index";
    }

    @RequestMapping("/assignPermission")
    public String assignPermission(){
        return "role/assignPermission";
    }

    @ResponseBody
    @RequestMapping("/loadDataAsync")
    public Object loadDataAsync(Integer roleid){

        List<Permission> root = new ArrayList<Permission>();

        List<Permission> childredPermissons =  permissionService.queryAllPermission();

        //根据角色id查询该角色之前所分配过的许可.
        List<Integer> permissonIdsForRoleid = permissionService.queryPermissionidsByRoleid(roleid);
        Map<Integer, Permission> map = new HashMap<Integer, Permission>();

        for (Permission innerpermission  : childredPermissons) {
            map.put(innerpermission.getId(), innerpermission );
            if (permissonIdsForRoleid.contains(innerpermission.getId())){
                innerpermission.setChecked(true);
            }
        }

        for (Permission permission : childredPermissons) {
            //通过子查找父
            //子菜单
            Permission child = permission ; //假设为子菜单
            if(child.getPid() == null){
                root.add(permission);
            }else{
                //父节点
                Permission parent = map.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return root;
    }



    @ResponseBody
    @RequestMapping("/doAssignPermission")
    public Object pageQuery(int roleid, Data datas){

        AjaxResult result = new AjaxResult();
        try {
            int res = roleService.assignPermission(roleid,datas);
            result.setSuccess(res==datas.getIds().size());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("分配许可失败！");
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/pageQuery")
    public Object pageQuery(int pageno,int pagesize){
        AjaxResult result = new AjaxResult();
        try {
            Map<String,Object> paramMap  = new HashMap<String,Object>();
            Page page = new Page(pageno,pagesize);
            Page pageRes;

            paramMap.put("page",page);

            pageRes = roleService.queryList(paramMap);
            result.setPage(pageRes);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("加载失败！");
            e.printStackTrace();
        }
        return result;
    }
}
