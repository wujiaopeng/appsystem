package com.appsys.interceptor;

import com.appsys.pojo.BackendUser;
import com.appsys.pojo.DevUser;
import com.appsys.tools.Constants;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: guoge
 * @create: 2018-12-12 17:34
 * @description: 系统拦截器
 **/
public class SysInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = Logger.getLogger(SysInterceptor.class);
    //定义不需要拦截的访问路径
    public static final String[] IGNORE_URL={"dev/login","dev/dologin","backend/login","backend/dologin","statics/"};

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.debug("SysInterceptor preHandle ==========================");
        //登录判断，判断session里面是否有user
        boolean flag=false;
        String path=request.getServletPath();

        for(String url:IGNORE_URL){
            if(path.contains(url)){
                flag=true;
                break;
            }
        }

        if(!flag){
        	logger.debug(" ==========================拦截成功");
            if(request.getServletPath().contains("dev")){
            	DevUser devUser=(DevUser)request.getSession().getAttribute(Constants.DEV_USER_SESSION);
            	if(devUser != null){
                    flag = true;
                }
                else {
                    request.setAttribute("error","你还没登录");
                    response.sendRedirect(request.getContextPath()+"/nologin.jsp");
                }
            }
            else if(request.getServletPath().contains("backend")){
            	BackendUser backendUser=(BackendUser)request.getSession().getAttribute(Constants.USER_SESSION);
            	if(backendUser != null){
            		flag = true;
                }
                else {
                    request.setAttribute("error","你还没登录");
                    response.sendRedirect(request.getContextPath()+"/nologin.jsp");
                }
            }
        }
        return flag;
    }
}
