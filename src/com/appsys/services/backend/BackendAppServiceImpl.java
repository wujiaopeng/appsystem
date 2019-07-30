package com.appsys.services.backend;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.appsys.dao.backend.BackendAppMapper;
import com.appsys.pojo.AppCategory;
import com.appsys.pojo.AppInfo;
import com.appsys.pojo.AppVersion;
import com.appsys.pojo.DataDictionary;

@Service
public class BackendAppServiceImpl implements BackendAppService {
	Logger logger=Logger.getLogger(BackendUserServiceImpl.class);
	
	@Resource
	private BackendAppMapper backendAppMapper;
	
	
	/**
     * 通过类型编码和类型Id查询类型类
     * @param typeCode
     * @param valueId
     */
	public DataDictionary getDataDictionary(String typeCode, int valueId)
			throws Exception {
		DataDictionary datadictionary=null;
		datadictionary=backendAppMapper.getDataDictionaryByTypeCodeAndValueId(typeCode, valueId);
		
		if(datadictionary != null){ //判断查到的类型类不为空
			return datadictionary;
		}
		return null;
	}
	
	/**
     * 通过和分页条件查询APP列表
     * @param addInfo
     * @return
     */
	public List<AppInfo> getAPPList(Map<String,Object> map) throws Exception{
		List<AppInfo> appInfoList=null;
		appInfoList=backendAppMapper.getAPPList(map);
		if(appInfoList != null){
			return appInfoList;
		}
		else{
			return null;
		}
	}
	
	/**
     * 得到查询到的APP条数
     * @param
     * @return
     */
    public int getAPPCount(AppInfo appInfo){
    	int count=0;
    	count=backendAppMapper.getAPPCount(appInfo);
    	return count;
    }

	
	/**
     * 获取所有所属平台列表
     * @param
     * @return
     */
    public List<DataDictionary> getAPPFlatForm() throws Exception{
    	List<DataDictionary> dataDictionaryList=null;
    	dataDictionaryList=backendAppMapper.getAPPFlatForm();
    	if(dataDictionaryList != null){
			return dataDictionaryList;
		}
		else {
			return null;
		}
    }
    
    /**
     * 通过id得到APPInfo类
     * @param
     * @return
     */
    public AppInfo getAPPInfoById(Integer id){
    	AppInfo appinfo=backendAppMapper.getAPPInfoById(id);
		Integer type1=appinfo.getCategoryLevel1();
		appinfo.setCategoryLevel1Name(getCategoryNameById(type1));
		Integer type2=appinfo.getCategoryLevel2();
		appinfo.setCategoryLevel2Name(getCategoryNameById(type2));
		Integer type3=appinfo.getCategoryLevel3();
		appinfo.setCategoryLevel3Name(getCategoryNameById(type3));
		Integer status=appinfo.getStatus();
		appinfo.setStatusName(getValueName(status, "APP_STATUS"));
		Integer flatformId=appinfo.getFlatformId();
		appinfo.setFlatformName(getValueName(flatformId, "APP_FLATFORM"));
		
		return appinfo;
    }
    /**
     * 通过id得到APPVersion类
     * @param
     * @return
     */
    public AppVersion getAPPVersionById(Integer id){
    	AppVersion appVersion=null;
    	appVersion=backendAppMapper.getAPPVersionById(id);
    	return appVersion;
    }
	
	/**
     * 通过id 查询类别名 
     * @param
     * @return
     */
	@Override
	public String getCategoryNameById(Integer id) {
		
		String typeName=backendAppMapper.selectCategoryNameById(id);	
		return typeName;
	}

	/**
     * 通过typeCode和valueID，查找字典中对应的名字
     * @param
     * @return
     */
	@Override
	public String getValueName(Integer valueId, String typeCode) {
		
		String valueName=backendAppMapper.selectValueNameByIdAndTypeCode(valueId, typeCode);
		return valueName;
	}
    
    /**
     * 通过审核结果和id更新APP状态
     * @param
     * @return
     */
    public int updateAPPStatus(Integer id, Integer status){
    	int count=0;
    	count=backendAppMapper.updateAPPStatus(id, status);
    	return count;
    }

	@Override
	public List<AppInfo> getAllAPPList() throws Exception {

		return backendAppMapper.getAllAPPList();
	}

	@Override
	public List<AppInfo> getGameList() throws Exception {

		return backendAppMapper.getGameList();
	}

	@Override
	public List<AppInfo> getApplicationList() throws Exception {

		return backendAppMapper.getApplicationList();
	}

	@Override
	public List<AppCategory> selectCategoryBypId(Integer parentId) throws Exception {
		List<AppCategory> appCategoryList=null;
		appCategoryList=backendAppMapper.selectCategoryBypId(parentId);
		if(appCategoryList != null){
			return appCategoryList;
		}
		else {
			return null;
		}
	}

	@Override
	public List<AppInfo> getCheckedAPPList(Integer currentPageNo, Integer pageSize) throws Exception {
		
		return backendAppMapper.getCheckedAPPList(currentPageNo, pageSize);
	}

	@Override
	public int getCheckedAllCount() throws Exception {
		
		return backendAppMapper.getCheckedAllCount();
	}

	@Override
	public int getCheckedNotCount() throws Exception {
		
		return backendAppMapper.getCheckedNotCount();
	}
	
	

}
