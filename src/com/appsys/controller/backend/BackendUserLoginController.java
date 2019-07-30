package com.appsys.controller.backend;

import com.appsys.pojo.AppInfo;
import com.appsys.pojo.BackendUser;
import com.appsys.pojo.DataDictionary;
import com.appsys.services.backend.BackendAppService;
import com.appsys.services.backend.BackendUserService;
import com.appsys.tools.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: cwx
 * @create: 2018-12-12 16:27
 * @description: 系统管理员登录的控制器
 **/
@Controller
@RequestMapping("/backend")
public class BackendUserLoginController {
    private static Logger logger=Logger.getLogger(BackendUserLoginController.class);

    @Resource
    private BackendUserService backendUserService;
    
    @Resource 
    private BackendAppService backendAppService;

    @RequestMapping(value = "/login")
    public String tologin(HttpServletRequest request, @ModelAttribute String error){
    	logger.info("进入系统管理员登录页--------------------");
    	if(error != null){
			request.setAttribute("error", error);
		}
    	
        return "backendlogin";
    }

    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public String dologin(@RequestParam String userCode,
                          @RequestParam String userPassword,
                          RedirectAttributes attr,
                          HttpSession session) throws Exception{
        logger.info("进入系统管理员登录判断--------------------");
        BackendUser backendUser=backendUserService.login(userCode,userPassword);

        if(backendUser != null){
        	String typeCode="USER_TYPE";
        	DataDictionary dataDictionary=backendAppService.getDataDictionary(typeCode, backendUser.getUserType());
        	if(dataDictionary != null){
        		session.setAttribute("dataDictionary",dataDictionary);
        		session.setAttribute(Constants.USER_SESSION,backendUser);
        		return "redirect:/backend/main";
        	}
        	else{
        		attr.addFlashAttribute("error", "查询到的类型类为空！");
        		return "redirect:/backend/login";
        	}
        } else{
            //页面跳转到（login.jsp）
        	attr.addFlashAttribute("error", "账号或密码错误！");
            return "redirect:/backend/login";
        }
    }

    @RequestMapping("/main")
    public String main( HttpSession session, RedirectAttributes attr,Model model) throws Exception{
    	logger.info("进入/backend/main--------------------");
        if (session.getAttribute(Constants.USER_SESSION) !=null){
        	List<AppInfo>   allAppList=backendAppService.getAllAPPList();
            List<AppInfo>   applicationList=backendAppService.getApplicationList();
            List<AppInfo>   allGameList=backendAppService.getGameList();
            model.addAttribute("allAppList",allAppList);
            model.addAttribute("applicationList",applicationList);
            model.addAttribute("allGameList",allGameList);
            return "backend/backendmain";
        }else {
        	attr.addFlashAttribute("error", "账号或密码错误！");
			return "redirect:/backend/login";
        }
    }
    
    @RequestMapping("/logout")
    public String logout( HttpSession session,Model model){
    	logger.info("进入/backend/logout--------------------");
        session.removeAttribute(Constants.USER_SESSION);
        return "redirect:/backend/login";
        
    }

}
