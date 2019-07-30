package com.appsys.services.backend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.appsys.pojo.BackendUser;
import com.appsys.pojo.DevUser;

/**
 * @author: cwx
 * @create: 2018-12-12 15:56
 * @description: 系统管理员业务类接口
 **/
public interface BackendUserService {

    /**
     * 通过用户编码和密码，登录
     * @param userCode
     * @param userPassword
     * @return
     * @throws Exception
     */
    public BackendUser login(String userCode,String userPassword) throws Exception;
    
    /*
     * 查询所用的开发者信息
     *
     */
    public  List<DevUser> getDevUserList(Integer currentPageNo,Integer pageSize) throws Exception;
    /*
     * 查询开发者的数量
     */
    public int getDevUserCount();
    
    
    
}
