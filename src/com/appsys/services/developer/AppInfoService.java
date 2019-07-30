package com.appsys.services.developer;

import java.util.List;
import java.util.Map;

import com.appsys.pojo.AppCategory;
import com.appsys.pojo.AppInfo;
import com.appsys.pojo.DataDictionary;
import com.appsys.pojo.DevUser;

/*
 * 开发者业务实现类
 */

public interface AppInfoService {
	
	/*
	 * 通过性数据库中添加一条App信息
	 */
	public boolean addAppInfo(AppInfo appinfo);
	/*
	 * 通过类型编码获取数据字典中的所有信息
	 */
	public List<DataDictionary> getDataDictionary(String typeCode) throws Exception;
	/*
	 * 通过父级节点id获取app类别列表
	 */
	public List<AppCategory> getCategoryList(Integer parentId);
	/*
	 * 通过传入apk名称查看名称是否被占用
	 */
	public boolean isExistAPKName(String APKName);
	/*
	 * 通过Appid获取App的所有信息
	 */
	public AppInfo getAppInfo(Integer id);
	/*
	 * 通过App类型id查找App类型的所有信息
	 */
	public String getCategoryNameById(Integer id);
	/*
	 * 通过类型值和类型编码到数据字典表中查找类型名
	 */
	public String getValueName(Integer valueId,String typeCode);
	/*
	 * 通过Appid删除logo图片的相关信息
	 */
	public boolean delLogo(Integer id);
	/*
	 * 修改Appinfo信息
	 */
	public boolean modifyAppInfo(AppInfo appInfo);
	/*
	 * 通过一些查询条件封装在map集合中，向数据库中查询APP信息列表
	 */
	public List<AppInfo> getAppInfoList(Map<String,Object> map);
	/*
	 * 通过一些查询条件封装在map集合中，查询满足条件的数据有多少条
	 */
	public int getAppInfoCount(Map<String,Object> map);
	/*
	 * 通过Appid删除树App信息
	 */
	public boolean delAppInfo(Integer id);
	/*
	 * 对App上下架相关操作
	 */
	public boolean onSaleOroffSale(Map<String,Object> map);
	/*
	 * 更新APPInfo表中最新版本信息
	 */
	public boolean modifyVersionId(Integer id);
	/*
	 * 通过开发者编码和密码登入
	 */
	public DevUser selectDevUser(String devCode,String devPassword);

}
