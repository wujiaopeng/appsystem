package com.appsys.dao.developer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.appsys.pojo.AppVersion;

public interface AppVersionMapper {
	// 通过App软件的id查询版本信息列表 
	public List<AppVersion> selectVersionListByAppId(@Param("appId")Integer appId);
	//通过表单提交的数据，向app_version表中添加一条数据 
	public int addAppVersion(AppVersion appversion);
	//通过版本Id，进行修改最新版本记录
	public int modifyAppVersion(AppVersion appversion);
	//通过ID，查询最新版本信息
	public AppVersion selectAppVersionById(@Param("versionId") Integer versionId); 
	//通过版本Id，进行删除最新版本的文件记录
	public int deleteFileById(@Param("versionId")Integer versionId);

}
