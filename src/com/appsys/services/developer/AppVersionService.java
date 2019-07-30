package com.appsys.services.developer;

import java.util.List;

import com.appsys.pojo.AppVersion;

public interface AppVersionService {
	
	/*
	 * 通过appId获取历史版本列表信息
	 */
	public List<AppVersion> getVersionList(Integer appId);
	/*
	 * 添加最新版本信息
	 */
	public boolean addAppVersion(AppVersion appversion);
	/*
	 * 修改最新版本信息
	 */
	public boolean modifyAppVersion(AppVersion appversion);
	/*
	 * 通过版本id查找版本所有信息
	 */
	public AppVersion findAppVersionBy(Integer versionId);
	/*
	 * 通过版本id删除，apk文件信息
	 */
	public boolean delFile(Integer versionId);

}
