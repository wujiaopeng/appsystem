package com.appsys.controller.developer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appsys.pojo.AppCategory;
import com.appsys.pojo.AppInfo;
import com.appsys.pojo.AppVersion;
import com.appsys.pojo.DataDictionary;
import com.appsys.pojo.DevUser;
import com.appsys.services.developer.AppInfoService;
import com.appsys.services.developer.AppVersionService;
import com.appsys.tools.Constants;
import com.appsys.tools.PageSupport;

/*
 * 开发者对App信息的操作，增删改查
 */
@Controller
@RequestMapping("/dev")
public class AppInfoController {
	
	private Logger logger=Logger.getLogger(AppInfoController.class);
	@Resource
	private AppInfoService appInfoService;
	@Resource 
	private AppVersionService appVersionService;
	
	
	//去登录页面
	@RequestMapping("/login")
	public String login(HttpServletRequest request,@ModelAttribute String error) {
		if(error != null){
			request.setAttribute("error", error);
		}
		return "devLogin";
	}
	//处理登录提交表单判断密码是否正确
	@RequestMapping(value="/dologin",method = RequestMethod.POST)
	public String dologin(@RequestParam String devCode,
	            @RequestParam String devPassword,
	            RedirectAttributes attr,
	            HttpSession session) {
		DevUser devUser=appInfoService.selectDevUser(devCode, devPassword);
		if(devUser!=null) {
			session.setAttribute(Constants.DEV_USER_SESSION, devUser);
			return "developer/devmain";
		}else {
			attr.addFlashAttribute("error", "账号或密码错误！");
	        return "redirect:/dev/login";
		}	
	}
	
	//点击退出登录
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute(Constants.DEV_USER_SESSION, null);
		return "devLogin";
	}
	
	
	//APP查询列表页面点击新增APP基础信息,去新增页面
	@RequestMapping("/addAppInfo")
	public String addAppInfo() {
		
		return "developer/addAppInfo";
	}
	
	//动态加载app所属平台列表
	@RequestMapping(value="/getAppFlatform",method=RequestMethod.GET)
	@ResponseBody
	public Object getAppFlatform(@RequestParam String typeCode) throws Exception {
		logger.info("****************************"+typeCode+"*******************************");
		List<DataDictionary> floatFormList=new ArrayList<DataDictionary>();
		floatFormList=appInfoService.getDataDictionary(typeCode);
		return floatFormList;
	}
	
	//动态加载APP分类
	@RequestMapping(value="/categorylevellist",method=RequestMethod.GET)
	@ResponseBody
	public Object getCategoryList(@RequestParam Integer pid) {
		logger.info("****************************"+pid+"*******************************");
		List<AppCategory> categoryList=appInfoService.getCategoryList(pid);
		
		return categoryList;
	} 
	
	//动态验证APK命名是否被占用
	@RequestMapping(value="/apkexist",method=RequestMethod.GET)
	@ResponseBody
	public Object apkexist(@RequestParam String APKName) {
		logger.info("****************************"+APKName+"*******************************");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		if(APKName==null || APKName.equals("")) {
			resultMap.put("APKName", "empty");
		}else {
			boolean flag=appInfoService.isExistAPKName(APKName);
			if(flag) {
				resultMap.put("APKName", "exist");
			}else {
				resultMap.put("APKName", "noexist");
			}
		}
		return resultMap;
	}
	
	//将表单提交的数据，保存多app_info数据库中
	@RequestMapping(value="/saveAppInfo",method=RequestMethod.POST)
	public String saveAppInfo(AppInfo appInfo,Model model,
							HttpServletRequest request,HttpSession session,
							@RequestParam(value="a_logoPicPath",required=false) MultipartFile multipartFile) {
		
		String errorMsg=null;//错误信息
		String logoPicPath=null;//LOGO图片URL路径
	    String logoLocPath=null;//LOGO图片的服务器存储路径
	    //定义上传路径
		String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadFile");
		if(!multipartFile.isEmpty()) {
			//获取源文件名
			String  fileName=multipartFile.getOriginalFilename();
			//获取文件后缀
			String fileSuffix=FilenameUtils.getExtension(fileName);
			logger.info("**************************"+multipartFile.getSize());
			if(multipartFile.getSize()>50000) {
				errorMsg="上传图片太大，文件大小不能超过50kb";
				model.addAttribute("errorMsg", errorMsg);
				return "developer/addAppInfo";
			}else if(fileSuffix.equalsIgnoreCase("jpg")||
					fileSuffix.equalsIgnoreCase("jpeg")||
					fileSuffix.equalsIgnoreCase("png")) {
				String newFileName= fileName;	
				File file=new File(path,newFileName);
				if(!file.exists()) {
					file.mkdirs();
				}
				try {
					multipartFile.transferTo(file);
					logoPicPath=request.getSession().getServletContext().getContextPath()+"/statics/uploadfiles/"+newFileName;
					logoLocPath=path+File.separator+newFileName;
				} catch (IOException e) {
					errorMsg="图片上传异常，请重试";
					model.addAttribute("errorMsg", errorMsg);
					return "developer/addAppInfo";
				}
			}else {
				errorMsg="图片上传格式有误，请重试";
				model.addAttribute("errorMsg", errorMsg);
				return "developer/addAppInfo";
			}
		}
		appInfo.setDevId(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appInfo.setStatus(1);
		appInfo.setCreationDate(new Date());
		appInfo.setCreatedBy(1);
		appInfo.setLogoPicPath(logoPicPath);
		appInfo.setLogoLocPath(logoLocPath);
		if(appInfoService.addAppInfo(appInfo)) {
			return "redirect:/dev/appInfoList";
		}
		return "developer/addAppInfo";
	}
	
	//点击查看app基础信息
	@RequestMapping(value="/appinfoView//{id}",method=RequestMethod.GET)
	public String appInfoView(@PathVariable Integer id,Model model) {
		AppInfo appinfo = appInfoService.getAppInfo(id);
		List<AppVersion> versionList=appVersionService.getVersionList(id);
		model.addAttribute("versionList", versionList);
		model.addAttribute("appinfo", appinfo);
		return "developer/appinfoView";
	}
	
	//点击修改App基础信息，去修改页面
	@RequestMapping(value="/modifyAppInfo",method=RequestMethod.GET)
	public String modifyAppInfo(@RequestParam Integer id,Model model) {
		AppInfo appinfo=appInfoService.getAppInfo(id);
		model.addAttribute("appinfo", appinfo);
		
		return "developer/modifyAppInfo";
	}
	//点击删除文件，异步请求，删除数据库中Logo的文件记录
	@RequestMapping(value="/delLogo",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteLogoBy(@RequestParam Integer id) {
		logger.info("****************************"+id+"*******************************");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
		if(appInfoService.delLogo(id)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "failed");
		}
		return resultMap;
	}
	
	//点击保存提交表单数据,修改记录信息
	@RequestMapping(value="/saveModifyAppInfo",method=RequestMethod.POST)
	public String saveModifyAppInfo(AppInfo appInfo,Model model,
			HttpServletRequest request,HttpSession session,
			@RequestParam(value="a_logoPicPath",required=false) MultipartFile multipartFile) {
		
		String errorMsg=null;//错误信息
		String logoPicPath=null;//LOGO图片URL路径
	    String logoLocPath=null;//LOGO图片的服务器存储路径
	    boolean flag=true;
	    //定义上传路径
		String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadFiles");
		if(!multipartFile.isEmpty()) {
			//获取源文件名
			String  fileName=multipartFile.getOriginalFilename();
			//获取文件后缀
			String fileSuffix=FilenameUtils.getExtension(fileName);
			logger.info("**************************"+multipartFile.getSize());
			if(multipartFile.getSize()>50000) {
				errorMsg="上传图片太大，文件大小不能超过50kb";
				flag=false;
			}else if(fileSuffix.equalsIgnoreCase("jpg")||
					fileSuffix.equalsIgnoreCase("jpeg")||
					fileSuffix.equalsIgnoreCase("png")) {
				String newFileName= fileName;	
				File file=new File(path,newFileName);
				if(!file.exists()) {
					file.mkdirs();
				}
				try {
					multipartFile.transferTo(file);
					logoPicPath=request.getSession().getServletContext().getContextPath()+"/statics/uploadfiles/"+newFileName;
					logoLocPath=path+File.separator+newFileName;
				} catch (IOException e) {
					errorMsg="图片上传异常，请重试";
					flag=false;
				}
			}else {
				errorMsg="图片上传格式有误，请重试";
				flag=false;
			}
		}
		if(flag) {
			appInfo.setModifyBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
			appInfo.setModifyDate(new Date());
			appInfo.setLogoPicPath(logoPicPath);
			appInfo.setLogoLocPath(logoLocPath);
			if(appInfoService.modifyAppInfo(appInfo)) {
				return "redirect:/dev/appInfoList";
			}else {
				model.addAttribute("errorMsg",errorMsg);
				model.addAttribute("id",appInfo.getId());
				return "redirect:/dev/modifyAppInfo";
			}
		}else {
			model.addAttribute("errorMsg",errorMsg);
			model.addAttribute("id",appInfo.getId());
			return "redirect:/dev/modifyAppInfo";
		}
		
		
		
	}

	//点击条件查询
	@RequestMapping(value="/appInfoList")
	public String appInfoList(Model model,AppInfo appinfo,
				@RequestParam(value="pageIndex",required=false) String pageIndex,
				HttpSession session) {
		logger.debug("开始****************************************"+pageIndex);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("softwareName", appinfo.getSoftwareName());
		map.put("devId", ((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		map.put("status",appinfo.getStatus() );
		map.put("flatformId", appinfo.getFlatformId());
		map.put("categoryLevel1", appinfo.getCategoryLevel1());
		map.put("categoryLevel2", appinfo.getCategoryLevel2());
		map.put("categoryLevel3", appinfo.getCategoryLevel3());
		//设置页面容量
    	int pageSize=Constants.pageSize;
    	//当前页码
    	int currentPageNo = 1;
    	if(pageIndex != null){
    		try{
    			currentPageNo = Integer.valueOf(pageIndex);
    		}catch(NumberFormatException e){
    			return "redirect:/user/syserror.html";
    			//response.sendRedirect("syserror.jsp");
    		}
    	}
    	//总数量（表）	
    	int totalCount	=appInfoService.getAppInfoCount(map);
    	//总页数
    	PageSupport pages=new PageSupport();
    	pages.setCurrentPageNo(currentPageNo);
    	pages.setPageSize(pageSize);
    	pages.setTotalCount(totalCount);
    	int totalPageCount = pages.getTotalPageCount();//总页数
    	//控制首页和尾页
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
    	logger.debug("*********************************"+(currentPageNo-1)*pageSize);
    	map.put("currentPageNo", (currentPageNo-1)*pageSize);
    	map.put("pageSize", pageSize);
		List<AppInfo> appinfoList=appInfoService.getAppInfoList(map);
		model.addAttribute("appinfoList", appinfoList);
		model.addAttribute("queryappinfo", appinfo);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "developer/appInfoList";
	}
	
	//点击删除，异步请求删除App
	@RequestMapping(value="/delapp",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteApp(@RequestParam Integer id) {
		logger.info("****************************"+id+"*******************************");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
		if(appInfoService.delAppInfo(id)) {
			resultMap.put("delResult", "true");
		}else {
			resultMap.put("delResult", "false");
		}
		return resultMap;
	}
	
	//点击app上架或下架，异步请求
	@RequestMapping(value="/sale",method=RequestMethod.GET)
	@ResponseBody
	public Object sale(@RequestParam Integer id,@RequestParam Integer status) {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		logger.info("****************************"+status+"*******************************");
		Map<String,Object> map=new HashMap<String,Object>();
		resultMap.put("errorCode", 0);
		
		if(status==4) {
			map.put("id", id);
			map.put("offSaleDate", new Date());
			map.put("status", 5);
		}else {
			map.put("id", id);
			map.put("onSaleDate", new Date());
			map.put("status", 4);
		}
		if(appInfoService.onSaleOroffSale(map)) {
			resultMap.put("resultMsg", "success");
		}else {
			resultMap.put("resultMsg", "failed");
		}
		return resultMap;
	}
}
