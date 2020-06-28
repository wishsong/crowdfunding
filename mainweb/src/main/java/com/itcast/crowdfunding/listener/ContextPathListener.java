package com.itcast.crowdfunding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextPathListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //System.out.println("监听器执行。。。"); 用于测试监听器的是否执行
        ServletContext application = servletContextEvent.getServletContext();
        String contextPath = application.getContextPath();
        //通过setAttribute的方式将项目路径contextPath放进ServletContext对象中，即applicationScope中
        application.setAttribute("APP_PATH",contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
