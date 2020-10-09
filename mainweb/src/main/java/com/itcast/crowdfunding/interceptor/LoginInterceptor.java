package com.itcast.crowdfunding.interceptor;


import com.itcast.crowdfunding.bean.User;
import com.itcast.crowdfunding.util.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.定义哪些路径是不需要拦截(将这些路径称为白名单)
        Set<String> uri = new HashSet<String>();
        uri.add("/index.htm");
        uri.add("/login.htm");
        uri.add("/doLogin.do");
        uri.add("/logout.do");

        //获取请求路径.
        String servletPath = request.getServletPath();
        if (uri.contains(servletPath)){
            return true;
        }

        //2.判断用户是否登录,如果登录就放行
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.LOGIN_USER);

        if (user != null){
            return true;
        }else {
            response.sendRedirect(request.getContextPath() + "/login.htm");
            return false;
        }
    }
}
