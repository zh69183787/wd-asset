package com.wonders.framework.security.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.security.LockedException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.wonders.framework.core.dto.UserDetailsObject;
import com.wonders.framework.core.service.OrganizationService;
import com.wonders.framework.exception.BizServiceException;
import com.wonders.framework.util.ServiceProvider;

/**
 * UserDetailService的实现
 * 
 * @author liming.feng
 * 
 */
public class UserDetailsServiceDBImpl implements UserDetailsService {

	/**
	 * 实现接口类中的方法 验证用户名和密码，并方便UserDetails在内部封装成GrantedAuthority
	 */
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException,
			LockedException {
		try {
			// 用户查询
			UserDetailsObject userObj = ServiceProvider.getService(
					OrganizationService.class).getUserByLoginId(userName);

			return userObj;
		} catch (BizServiceException e) {
			throw new UsernameNotFoundException(e.getErrorMessage());
		} catch (LockedException e) {
			throw e;
		} catch (Exception e) {
			throw new UsernameNotFoundException("请求出错！");
		}
	}

}