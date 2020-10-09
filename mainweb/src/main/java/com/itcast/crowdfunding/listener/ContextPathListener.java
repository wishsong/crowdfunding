package com.itcast.crowdfunding.listener;

import com.itcast.crowdfunding.bean.Permission;
import com.itcast.crowdfunding.manager.service.PermissionService;
import com.itcast.crowdfunding.util.Const;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContextPathListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //System.out.println("监听器执行。。。"); 用于测试监听器的是否执行
        ServletContext application = servletContextEvent.getServletContext();
        String contextPath = application.getContextPath();
        //通过setAttribute的方式将项目路径contextPath放进ServletContext对象中，即applicationScope中
        application.setAttribute("APP_PATH",contextPath);

        //2.加载所有许可路径
        ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(application);
        PermissionService permissionService = ioc.getBean(PermissionService.class);
        List<Permission> queryAllPermission = permissionService.queryAllPermission();

        Set<String> allURIs = new HashSet<String>();

        for (Permission permission : queryAllPermission) {
            allURIs.add("/" + permission.getUrl());
        }
        application.setAttribute(Const.ALL_PERMISSION_URI, allURIs);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
