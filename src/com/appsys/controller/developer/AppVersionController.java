package com.appsys.controller.developer;

import java.io.File;
import java.io.IOException;
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
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appsys.pojo.AppVersion;
import com.appsys.pojo.DevUser;
import com.appsys.services.developer.AppInfoService;
import com.appsys.services.developer.AppVersionService;
import com.appsys.tools.Constants;

@Controller
@RequestMapping("/dev")
public class AppVersionController{
	private Logger logger=Logger.getLogger(AppVersionController.class);
	
	@Resource
	private AppVersionService appVersionService;
	@Resource
	private AppInfoService appInfoService;
	//点击添加新增版本，去新增页面
	@RequestMapping(value="/addAppversion",method=RequestMethod.GET)
	public String addAppversion(@RequestParam(value="appId",required=true) Integer appId,Model model) {
		List<AppVersion> versionList=appVersionService.getVersionList(appId);
		model.addAttribute("versionList", versionList);
		model.addAttribute("appId",appId);
		return "developer/addAppversion";
	}
	//提交表单，添加APP版本信息数据
	@RequestMapping(value="/saveAppversion",method=RequestMethod.POST)
	public String saveAppVersion(AppVersion appversion,Model model,
						HttpServletRequest request,HttpSession session,
						@RequestParam(value="a_apkLocPath",required=false) MultipartFile multipartFile) {
		String errorMsg="";//错误信息
		String apkFileName=null;//apk文件名
		String downloadLink=null;//apk下载路径
	    String apkLocPath=null;//apk服务器存储路径
	    boolean flag=true;
	    //定义上传路径
  		String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadFiles");
  		if(!multipartFile.isEmpty()) {
  			//获取源文件名
  			String  fileName=multipartFile.getOriginalFilename();
  			apkFileName=fileName;
  			//获取文件后缀
  			String fileSuffix=FilenameUtils.getExtension(fileName);
  			logger.info("**************************"+multipartFile.getSize());
  			if(multipartFile.getSize()>500000000) {
  				errorMsg="上传图片太大，文件大小不能超过50kb";
  				flag=false;
  			}else if(fileSuffix.equalsIgnoreCase("apk")) {
  				String newFileName= fileName;	
  				File file=new File(path,newFileName);
  				if(!file.exists()) {
  					file.mkdirs();
  				}
  				try {
  					multipartFile.transferTo(file);
  					downloadLink=request.getSession().getServletContext().getContextPath()+"/statics/uploadfiles/"+newFileName;
  					apkLocPath=path+File.separator+newFileName;
  				} catch (IOException e) {
  					errorMsg="文件上传异常，请重试";
  					flag=false;
  				}
  			}else {
  				errorMsg="文件上传格式有误，请重试";
  				flag=false;
  			}
  		}
  		if(flag) {
  			appversion.setApkFileName(apkFileName);
  	  		appversion.setApkLocPath(apkLocPath);
  	  		appversion.setDownloadLink(downloadLink);
  	  		appversion.setPublishStatus(3);
  	  		appversion.setCreationDate(new Date());
  	  		appversion.setCreatedBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
  	  		if(appVersionService.addAppVersion(appversion)) {
  	  			boolean flag1=appInfoService.modifyVersionId(appversion.getAppId());
  	  			logger.debug("**********************************"+flag1);
  	  			return "redirect:/dev/appInfoList";
  	  		}else {
  	  		model.addAttribute("appId", appversion.getAppId());
  	  		model.addAttribute("errorMsg", errorMsg);
  			return "redirect:/dev/addAppversion";
  	  		}
  		}else {
  	  		model.addAttribute("appId", appversion.getAppId());
  	  		model.addAttribute("errorMsg", errorMsg);
  			return "redirect:/dev/addAppversion";
  		}
	}

	//点击修改最新版本页面 ，去修改页面
	@RequestMapping(value="/modifyAppversion")
	public String modifyAppversion(@RequestParam(value="appId",required=false) Integer appId,
									@RequestParam(value="versionId",required=true) Integer versionId,Model model) {
		List<AppVersion> versionList=appVersionService.getVersionList(appId);
		model.addAttribute("versionList", versionList);
		AppVersion appversion=appVersionService.findAppVersionBy(versionId);
		model.addAttribute("appversion", appversion);
		
		return "developer/modifyAppversion";
	}
	
	//下载文件
	@RequestMapping("/download")  
    public ResponseEntity<byte[]> export(@RequestParam(value="apkFileName")String apkFileName,HttpServletRequest request) throws IOException {  
    	
    	//获取下载文件路径
    	String path=request.getServletContext().getRealPath("/statics/uploadfiles");
        HttpHeaders headers = new HttpHeaders();    
        File file = new File(path+File.separator+apkFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        headers.setContentDispositionFormData("attachment", apkFileName);    
       
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                              headers, HttpStatus.CREATED);    
    } 
    
	//点击删除文件，异步请求，删除数据库中的文件记录
	@RequestMapping(value="/delfile",method=RequestMethod.GET)
	@ResponseBody
	public Object delFile(@RequestParam Integer versionId){
		logger.info("****************************"+versionId+"*******************************");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
		if(appVersionService.delFile(versionId)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "failed");
		}
		return resultMap;
	}
	
	
	
	//点击保存提交表单内容，将数据加入到数据库中修改App版本信息
	@RequestMapping(value="/saveModifyAppversion",method=RequestMethod.POST)
	public String saveModifyAppversion(AppVersion appversion,Model model,
			HttpServletRequest request,HttpSession session,
			@RequestParam(value="a_apkLocPath",required=false) MultipartFile multipartFile) {
		String errorMsg="";//错误信息
		String apkFileName=null;//apk文件名
		String downloadLink=null;//apk下载路径
	    String apkLocPath=null;//apk服务器存储路径
	    boolean flag=true;
	    //定义上传路径
  		String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadFiles");
  		if(!multipartFile.isEmpty()) {
  			//获取源文件名
  			String  fileName=multipartFile.getOriginalFilename();
  			apkFileName=fileName;
  			//获取文件后缀
  			String fileSuffix=FilenameUtils.getExtension(fileName);
  			logger.info("**************************"+multipartFile.getSize());
  			if(multipartFile.getSize()>500000000) {
  				errorMsg="上传图片太大，文件大小不能超过50kb";
  				flag=false;
  			}else if(fileSuffix.equalsIgnoreCase("apk")) {
  				String newFileName= fileName;
  				File file=new File(path,newFileName);
  				if(!file.exists()) {
  					file.mkdirs();
  				}
  				try {
  					multipartFile.transferTo(file);
  					downloadLink=request.getSession().getServletContext().getContextPath()+"/statics/uploadfiles/"+newFileName;
  					apkLocPath=path+File.separator+newFileName;
  				} catch (IOException e) {
  					errorMsg="文件上传异常，请重试";
  					flag=false;
  				}
  			}else {
  				errorMsg="文件上传格式有误，请重试";
  				flag=false;
  			}
  		}
  		if(flag) {
  			appversion.setApkFileName(apkFileName);
  	  		appversion.setApkLocPath(apkLocPath);
  	  		appversion.setDownloadLink(downloadLink);
  	  		appversion.setModifyDate(new Date());
  	  		appversion.setModifyBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
  	  		if(appVersionService.modifyAppVersion(appversion)) {
  	  			return "redirect:/dev/appInfoList";
  	  		}else {
  	  		model.addAttribute("appId", appversion.getAppId());
  	  		model.addAttribute("versionId", appversion.getAppId());
  			return "redirect:/dev/modifyAppversion";
  	  		}
  		}else {	
  	  		model.addAttribute("appId", appversion.getAppId());
  	  	    model.addAttribute("versionId", appversion.getAppId());
  	  		model.addAttribute("errorMsg", errorMsg);
  			return "redirect:/dev/modifyAppversion";
  		}
	}
}
