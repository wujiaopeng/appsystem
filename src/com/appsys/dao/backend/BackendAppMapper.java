package com.appsys.dao.backend;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.appsys.pojo.AppCategory;
import com.appsys.pojo.AppInfo;
import com.appsys.pojo.AppVersion;
import com.appsys.pojo.DataDictionary;

@Repository
public interface BackendAppMapper {
	
	/*
	 * 查询下载App排名信息
	 */
	public List<AppInfo> getAllAPPList() throws Exception;
	
	/*
	 * 查询下载游戏的排名信息
	 */
	public List<AppInfo> getGameList() throws Exception;
	
	/*
	 * 查询应用型app排名信息
	 */
	public List<AppInfo> getApplicationList() throws Exception;
	
	/**
     * 通过类型编码和类型Id查询类型类
     * @param typeCode
     * @param valueId
     */
    public DataDictionary getDataDictionaryByTypeCodeAndValueId(@Param("typeCode") String typeCode, @Param("valueId") int valueId) throws Exception;
    
    /**
     * 通过和分页条件查询APP列表
     * @param addInfo
     * @return
     */
    public List<AppInfo> getAPPList(Map<String,Object> map) throws Exception;
    
    /**
     * 得到查询到的APP条数
     * @param
     * @return
     */
    public int getAPPCount(AppInfo appInfo);
    
    /*
     * 查询已经审核的App信息
     */
    public List<AppInfo> getCheckedAPPList(@Param("currentPageNo")Integer currentPageNo,
    									@Param("pageSize")Integer pageSize) throws Exception;
    /*
     * 查询已经审核了的App数目
     */
    public int getCheckedAllCount()throws Exception;
    
    /*
     * 查询已经审核未通过的App数目
     */
    public int getCheckedNotCount()throws Exception;
    
    /**
     * 通过类型id得到类型名列表
     * @param categoryLevel
     * @return
     */
    public List<AppCategory> selectCategoryBypId(@Param("parentId") Integer parentId);
  
    
    /**
     * 获取所有所属平台列表
     * @param
     * @return
     */
    public List<DataDictionary> getAPPFlatForm();
    
    /**
     * 通过id得到APPInfo类
     * @param
     * @return
     */
    public AppInfo getAPPInfoById(@Param("id") Integer id);
    /**
     * 通过id得到APPVersion类
     * @param
     * @return
     */
    public AppVersion getAPPVersionById(@Param("id") Integer id);
    
	/**
     * 通过id 查询类别名 
     * @param
     * @return
     */
	public String selectCategoryNameById(@Param("id")Integer id);
	
	/**
     * 通过typeCode和valueID，查找字典中对应的名字
     * @param
     * @return
     */
	public String selectValueNameByIdAndTypeCode(@Param("valueId")Integer valueId,@Param("typeCode") String typeCode);
	
    /**
     * 通过审核结果和id更新APP状态
     * @param
     * @return
     */
    public int updateAPPStatus(@Param("id") Integer id, @Param("status") Integer status);
}
