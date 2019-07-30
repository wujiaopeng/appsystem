package com.appsys.services.backend;


import com.appsys.dao.backend.BackendUserMapper;
import com.appsys.pojo.BackendUser;
import com.appsys.pojo.DevUser;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author: cwx
 * @create: 2018-12-12 15:59
 * @description: 系统管理员业务类的实现
 **/
@Service
public class BackendUserServiceImpl implements BackendUserService {
	Logger logger=Logger.getLogger(BackendUserServiceImpl.class);

    @Resource
    private BackendUserMapper backendUserMapper;

    /**
     * 通过用户编码和密码，登录
     * @param userCode
     * @param userPassword
     * @return
     * @throws Exception
     */
    public BackendUser login(String userCode, String userPassword) throws Exception {
    	BackendUser backendUser=null;
    	backendUser = backendUserMapper.getBackendUserByCode(userCode);

        if(backendUser != null){ //判断用户不等于空
            if(backendUser.getUserPassword().equals(userPassword)){//判断查询出来的用户的密码和传递过来的密码是否相同
                return backendUser;
            }
        }
        return null;
    }

	@Override
	public List<DevUser> getDevUserList(Integer currentPageNo, Integer pageSize) throws Exception {
		
		return backendUserMapper.getDevUserList(currentPageNo, pageSize);
	}

	@Override
	public int getDevUserCount() {
		
		return backendUserMapper.getDevUserCount();
	}

    
    
}
