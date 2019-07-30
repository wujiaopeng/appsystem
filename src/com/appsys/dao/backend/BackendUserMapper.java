package com.appsys.dao.backend;

import java.util.List;

import com.appsys.pojo.AppCategory;
import com.appsys.pojo.AppInfo;
import com.appsys.pojo.AppVersion;
import com.appsys.pojo.BackendUser;
import com.appsys.pojo.DataDictionary;
import com.appsys.pojo.DevUser;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: guoge
 * @create: 2018-12-12 15:48
 * @description:
 **/
@Repository
public interface BackendUserMapper {

    /**
     * 通过用户编码查询用户
     * @param userCode
     * @return
     * @throws Exception
     */
    public BackendUser getBackendUserByCode(@Param("userCode") String userCode) throws Exception;
    
    /*
     * 查询所用的开发者信息
     *
     */
    public  List<DevUser> getDevUserList(@Param("currentPageNo")Integer currentPageNo,
    								@Param("pageSize")Integer pageSize) throws Exception;
    
    /*
     * 查询开发者的数量
     */
    public int getDevUserCount();
    
}