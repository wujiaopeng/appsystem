package com.appsys.controller.backend;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appsys.pojo.AppCategory;
import com.appsys.pojo.AppInfo;
import com.appsys.pojo.AppVersion;
import com.appsys.pojo.DataDictionary;
import com.appsys.pojo.DevUser;
import com.appsys.services.backend.BackendAppService;
import com.appsys.services.backend.BackendUserService;
import com.appsys.tools.Constants;
import com.appsys.tools.PageSupport;

@Controller
@RequestMapping("/backend")
public class BackendAppController {
	Logger logger=Logger.getLogger(BackendAppController.class);
	
	@Resource
	private BackendAppService backendAppService;
	@Resource
	private BackendUserService backendUserService;
	
	
	//根据条件获取APP列表
		@RequestMapping(value="/appList")
		public String getAPPList(AppInfo appInfo,Model model,
						@RequestParam(value="pageIndex",required=false) String pageIndex) throws Exception{
			logger.info("**************进入getAPPList方法");
			logger.info("**************softwareName:"+appInfo.getSoftwareName());
			logger.info("**************flatformId:"+appInfo.getFlatformId());
			logger.info("**************categoryLevel1:"+appInfo.getCategoryLevel1());
			logger.info("**************categoryLevel2:"+appInfo.getCategoryLevel2());
			logger.info("**************categoryLevel3:"+appInfo.getCategoryLevel3());
			logger.info("**************versionId:"+appInfo.getVersionId());
			
			logger.info("**************currentPageNo:"+pageIndex+"页");
			
			appInfo.setStatus(1);
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
	    	int totalCount	=backendAppService.getAPPCount(appInfo);
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
			
			List<AppInfo> appInfoList=null;
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("softwareName", appInfo.getSoftwareName());
			map.put("flatformId", appInfo.getFlatformId());
			map.put("categoryLevel1", appInfo.getCategoryLevel1());
			map.put("categoryLevel2", appInfo.getCategoryLevel2());
			map.put("categoryLevel3", appInfo.getCategoryLevel3());
			map.put("status", 1);
			map.put("currentPageNo", (currentPageNo-1)*pageSize);
	    	map.put("pageSize", pageSize);
			appInfoList=backendAppService.getAPPList(map);  //通过条件和分页查询APP列表
			if(appInfo != null){
				logger.debug("**************获取APP列表成功");
				model.addAttribute("appinfoList", appInfoList);
				//request.setAttribute("softwareName", appInfo.getSoftwareName());
			}
			else{
				logger.info("**************获取APP列表失败");
			}
			
			model.addAttribute("appinfo", appInfo);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("currentPageNo", currentPageNo);
			model.addAttribute("totalPageCount", totalPageCount);
			
			return "backend/appList";
		}
		
		
		//查询已经审核的app信息
		@RequestMapping("/checkedlist")
		public String getCheckedAppList(Model model,
				@RequestParam(value="pageIndex",required=false) String pageIndex)throws Exception {
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
	    	int totalCount	=backendAppService.getCheckedAllCount();
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
	    	List<AppInfo> appInfoList=backendAppService.getCheckedAPPList((currentPageNo-1)*pageSize, pageSize);
	    	
	    	model.addAttribute("appInfoList", appInfoList);
	    	model.addAttribute("totalCount", totalCount);
			model.addAttribute("currentPageNo", currentPageNo);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("noCheckedNum", backendAppService.getCheckedNotCount());
	    	
			return "backend/appchecked";
		}
		
		//获取一二三级分类列表
		//动态加载APP分类
		@RequestMapping(value="/categorylevellist",method=RequestMethod.GET)
		@ResponseBody
		public Object getCategoryList(@RequestParam Integer pid)throws Exception {
			logger.info("****************************"+pid+"*******************************");
			List<AppCategory> categoryList=backendAppService.selectCategoryBypId(pid);
			
			return categoryList;
		} 
		
		//获取所属平台列表
		@RequestMapping(value="/getAppFlatform")
		@ResponseBody
		public Object getAPPFlatForm() throws Exception{
			logger.info("**************进入getAPPFlatForm方法");
			
			List<DataDictionary> dataDictionaryList = null;
			dataDictionaryList = backendAppService.getAPPFlatForm();
			
			return dataDictionaryList;
		}
		
		//进入APP审核页
		@RequestMapping(value="/checkAPP")
		@ResponseBody
		public Object checkAPP(@RequestParam Integer id,
								@RequestParam Integer versionid,Model model){
			logger.info("*************id:"+id);
			
			AppInfo appInfo=null;
			AppVersion appVersion=null;
			Map<Object,Object> map=new HashMap<Object,Object>();
			appInfo=backendAppService.getAPPInfoById(id);
			appVersion=backendAppService.getAPPVersionById(versionid);
			
			if(appInfo != null){
				logger.info("*************获取APPInfo类成功！"+appInfo.getAPKName());
				map.put("appInfo", appInfo);
				//model.addAttribute("appinfo",appInfo);
			}else{
				logger.info("*************获取APPInfo类失败！");
			}
			if(appVersion != null){
				//model.addAttribute("appVersion",appVersion);
				map.put("appVersion", appVersion);
			}
			
			return map;
		}
		
		//上传apk测试
		@RequestMapping(value="/uploadAPK")
		public String uploadAPK(HttpServletRequest request, @RequestParam("file") MultipartFile file, HttpSession session) throws Exception{
			//测试
			logger.info("***********使用uploadAPK方法");
			logger.info("***********fName:"+file.getOriginalFilename());
			logger.info("***********fContentType:"+file.getContentType());
			
			//上传文件不为空
			if(!file.isEmpty()){
				//获取文件要去到的路径
				String path=request.getServletContext().getRealPath("/statics/uploadfiles");
				logger.info("***********path:"+path);
				//获取上传文件名
				String fileName=file.getOriginalFilename();
				//创建被上传文件传输的新文件
				File newFile= new File(path,fileName);
				//判断新文件的路径是否存在，不存在则新建
				if(!newFile.getParentFile().exists()){
					newFile.getParentFile().mkdirs();
				}
				
				//开始传输文件
				//将file传输到path中的fileName
				file.transferTo(new File(path+File.separator+fileName));
				logger.info("***********上传成功！");
				logger.info("***********上传文件路径："+(path+File.separator+fileName));
				return "redirect:/backend/appList";
			}
			else{
				logger.info("***********上传失败！");
				return "redirect:/backend/appList";
			}
			
		}
		
		//对审核页中的APK下载
		@RequestMapping(value="/downloadAPK")
		public void downloadFile(HttpServletRequest request, @RequestParam(value="apkFileName", required=false) String apkFileName, HttpServletResponse response) throws Exception{
			//测试
			logger.info("***********使用downloadFile方法");
			
			//获取下载文件路径
			String path=request.getServletContext().getRealPath("/statics/uploadfiles");
			logger.info("***********path:"+path);
			logger.info("***********apkFileName:"+apkFileName);
			
			File file= new File(path+File.separator+apkFileName);
			OutputStream out=null;
			
			if(file.exists()){
				logger.debug("************文件存在！");
				
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition", "attachment;fileName="+apkFileName);
				try{
					out=response.getOutputStream();
					out.write(FileUtils.readFileToByteArray(file));
					out.flush();
				}catch(IOException e){
					e.printStackTrace();
				}finally{
					if(out!=null)
						try{
							out.close();
						}catch(IOException e){
							e.printStackTrace();
						}
				}
			}

		}
		
		//通过审核结果和id更新APP状态
		@RequestMapping(value="/changeStuatus")
		@ResponseBody
		public Object updateAPPStatus(@RequestParam(value="appInfoId", required=false) Integer appInfoId,
									@RequestParam(value="status", required=false) Integer status,
									 HttpServletRequest request){
			//测试
			logger.info("****************进入updateAPPStatus");
			Map<String,Object> map=new HashMap<String,Object>();
		
			int count=backendAppService.updateAPPStatus(appInfoId, status);
			if(count>0) {
				map.put("result", "true");
			}else {
				map.put("result", "false");
			}
			
			return map;
			
		}
		
		//获取所有开发者的信息
		@RequestMapping(value="userlist")
		public String toDevUserManager(@RequestParam(value="pageIndex",required=false)String pageIndex,
										Model model)throws Exception {
			int pageSize=Constants.pageSize;
			int currentPageNo=1;
			if(pageIndex!=null) {
				try {
					currentPageNo=Integer.parseInt(pageIndex);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			int totalCount=backendUserService.getDevUserCount();
			PageSupport pageSupport=new PageSupport();
			pageSupport.setTotalCount(totalCount);
			pageSupport.setPageSize(pageSize);
			pageSupport.setCurrentPageNo(currentPageNo);
			int totalPageCount=pageSupport.getTotalPageCount();
			//控制首页和尾页
	    	if(currentPageNo < 1){
	    		currentPageNo = 1;
	    	}else if(currentPageNo > totalPageCount){
	    		currentPageNo = totalPageCount;
	    	}
	    	List<DevUser> devUserList=backendUserService.getDevUserList((currentPageNo-1)*pageSize, pageSize);
			
	    	model.addAttribute("totalCount", totalCount);
	    	model.addAttribute("currentPageNo", currentPageNo);
	    	model.addAttribute("totalPageCount",totalPageCount);
	    	model.addAttribute("devUserList", devUserList);
	    	
	    	return "backend/devuser_manager";
		}

}
