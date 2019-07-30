package com.appsys.dao.developer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.appsys.pojo.AppCategory;
import com.appsys.pojo.AppInfo;
import com.appsys.pojo.DataDictionary;
import com.appsys.pojo.DevUser;

/*
 * 用于操作APPInfo表中的数据，增删改查
 */
@Repository("appInfoMapper")
public interface AppInfoMapper {
	/*
	 * 通过表单提交的APPInfo对象，向数据表中添加一条数据
	 */
	public int addAppInfo(AppInfo appInfo);
	//通过typeCode查找出字典表中的内容
	public List<DataDictionary> selectDictionaryByTypeCode(@Param("typeCode") String typeCode) throws Exception;
	//通过typeCode和valueID，查找字典中对应的名字
	public String selectValueNameByIdAndTypeCode(@Param("valueId")Integer valueId,@Param("typeCode") String typeCode);
	//通过parentID查找所属类型
	public List<AppCategory> selectCategoryBypId(@Param("parentId") Integer parentId);
	//通过id 查询类别名 
	public String selectCategoryNameById(@Param("id")Integer id);
	//通过APK的名称查找APP
	public AppInfo selectAppInfoByAPKName(@Param("APKName") String APKName);
	//通过Id查找App的基础信息
	public AppInfo selectAppInfoById(@Param("appId")Integer id);
	//通过Id删除app的logo图片
	public int delLogoById(@Param("id")Integer id);
	//通过id对APPInfo表中的信息修改
	public int modifyAppInfoById(AppInfo appInfo);
	//通过条件查询的查询APP的基本信息
	public List<AppInfo> selectAppInfoList(Map<String,Object> map);
	//通过条件查询的查询APP信息结果的条数
	public int selectAppInfoNum(Map<String,Object> map);
	//通过appId删除App记录
	public int delAppInfoById(@Param("id") Integer id);
	//通过APPID对App上架或下架操作
	public int onSaleOroffSale(Map<String,Object> map);
	//更新App的最新版本
	public int updateVersionNo(@Param("id")Integer id);
	//通过开发者的code查询出devUser
	public DevUser selectDevUser(@Param("devCode") String devCode);
	
}
