package com.appsys.services.developer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.appsys.dao.developer.AppVersionMapper;
import com.appsys.pojo.AppVersion;

@Service
public class AppVersionServiceImpl implements AppVersionService {

	@Resource
	private AppVersionMapper appVersionMapper;
	@Override
	public List<AppVersion> getVersionList(Integer appId) {
		
		List<AppVersion> versionList=appVersionMapper.selectVersionListByAppId(appId);
		
		return versionList;
	}
	@Override
	public boolean addAppVersion(AppVersion appversion) {
		int num=appVersionMapper.addAppVersion(appversion);
		if(num>0) {
			return true;
		}
		return false;
	}
	@Override
	public boolean modifyAppVersion(AppVersion appversion) {
		int num=appVersionMapper.modifyAppVersion(appversion);
		if(num>0) {
			return true;
		}
		return false;
	}
	@Override
	public AppVersion findAppVersionBy(Integer versionId) {
		AppVersion appversion=appVersionMapper.selectAppVersionById(versionId);
		if(appversion!=null) {
			return appversion;
		}
		return null;
	}
	@Override
	public boolean delFile(Integer versionId) {
		int num=appVersionMapper.deleteFileById(versionId);
		if(num>0) {
			return true;
		}
		return false;
	}

}
