package com.wonders.framework.core.service;

import java.util.List;

import com.wonders.framework.core.dto.ResourceDetailsObject;
import com.wonders.framework.core.dto.UserDetailsObject;
import com.wonders.framework.exception.BizServiceException;

/**
 * 封装用户登录对外调用接口
 * 
 * @author zhangdingsheng
 * 
 */
public interface OrganizationService {

	/**
	 * 根据用户ID获得用户对象
	 * 
	 * @param
	 * @throws BizServiceException
	 */
	public UserDetailsObject getUserByLoginId(String loginId)
			throws BizServiceException;

	/**
	 * 得到所有的资源
	 * 
	 * @return
	 * @throws BizServiceException
	 */
	public List<ResourceDetailsObject> getAllResources() throws BizServiceException;

}
