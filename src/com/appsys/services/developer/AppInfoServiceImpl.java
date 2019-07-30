package com.appsys.services.developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.appsys.dao.developer.AppInfoMapper;
import com.appsys.pojo.AppCategory;
import com.appsys.pojo.AppInfo;
import com.appsys.pojo.DataDictionary;
import com.appsys.pojo.DevUser;
import com.sun.media.jfxmedia.logging.Logger;

@Service
public class AppInfoServiceImpl implements AppInfoService {
	@Resource()
	private AppInfoMapper appInfoMapper;
	
	
	public boolean addAppInfo(AppInfo appinfo) {
		boolean flag=false;
		int num=appInfoMapper.addAppInfo(appinfo);//判断改变的行数
		if(num>0) {
			flag=true;
		}else {
			flag=false;
		}
		return flag;
	}


	public List<DataDictionary> getDataDictionary(String typeCode)  throws Exception{
		
		List<DataDictionary> dataList=appInfoMapper.selectDictionaryByTypeCode(typeCode);
		
		return dataList;
	}


	@Override
	public List<AppCategory> getCategoryList(Integer parentId) {
		
		List<AppCategory> categoryList=appInfoMapper.selectCategoryBypId(parentId);
		
		return categoryList;
	}
	
	@Override
	public boolean isExistAPKName(String APKName) {
		AppInfo appInfo=appInfoMapper.selectAppInfoByAPKName(APKName);
		boolean flag=false;
		if(appInfo!=null) {
			flag=true;
		}
		return flag;
	}


	@Override
	public AppInfo getAppInfo(Integer id) {
		
		AppInfo appinfo=appInfoMapper.selectAppInfoById(id);
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


	@Override
	public String getCategoryNameById(Integer id) {
		
		String typeName=appInfoMapper.selectCategoryNameById(id);	
		return typeName;
	}


	@Override
	public String getValueName(Integer valueId, String typeCode) {
		
		String valueName=appInfoMapper.selectValueNameByIdAndTypeCode(valueId, typeCode);
		return valueName;
	}


	@Override
	public boolean delLogo(Integer id) {
		int num=appInfoMapper.delLogoById(id);
		if(num>0) {
			return true;
		}
		return false;
	}


	@Override
	public boolean modifyAppInfo(AppInfo appInfo) {
		int num=appInfoMapper.modifyAppInfoById(appInfo);
		if(num>0) {
			return true;
		}
		return false;
	}


	@Override
	public List<AppInfo> getAppInfoList(Map<String,Object> map) {
		List<AppInfo> appinfoList=appInfoMapper.selectAppInfoList(map);
		List<AppInfo> appinfoList1=new ArrayList<AppInfo>();
		for(AppInfo ai:appinfoList) {
			Integer type1=ai.getCategoryLevel1();
			ai.setCategoryLevel1Name(getCategoryNameById(type1));
			Integer type2=ai.getCategoryLevel2();
			ai.setCategoryLevel2Name(getCategoryNameById(type2));
			Integer type3=ai.getCategoryLevel3();
			ai.setCategoryLevel3Name(getCategoryNameById(type3));
			Integer status=ai.getStatus();
			ai.setStatusName(getValueName(status, "APP_STATUS"));
			Integer flatformId=ai.getFlatformId();
			ai.setFlatformName(getValueName(flatformId, "APP_FLATFORM"));
			appinfoList1.add(ai);
		}
		return appinfoList1;
	}


	@Override
	public int getAppInfoCount(Map<String,Object> map) {
		int num=appInfoMapper.selectAppInfoNum(map);
		return num;
	}


	@Override
	public boolean delAppInfo(Integer id) {
		int num=appInfoMapper.delAppInfoById(id);
		if(num>0) {
			return true;
		}
		return false;
	}


	@Override
	public boolean onSaleOroffSale(Map<String, Object> map) {
		int num=appInfoMapper.onSaleOroffSale(map);
		if(num>0) {
			return true;
		}
		return false;
	}


	@Override
	public boolean modifyVersionId(Integer id) {
		int num=appInfoMapper.updateVersionNo(id);
		if(num>0) {
			return true;
		}
		return false;
	}


	@Override
	public DevUser selectDevUser(String devCode, String devPassword) {
		DevUser devUser=appInfoMapper.selectDevUser(devCode);
		if(devUser!=null) {
			if(devUser.getDevPassword().equals(devPassword)) {
				return devUser;
			}
		}	
		return null;
	}

}
