package com.wonders.framework.core.dto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

/**
 * spring security 用户对象类
 * 
 * @author liming.feng
 * 
 */
public class UserDetailsObject implements UserDetails {

	/**
	 * userId
	 */
	private Integer userId;

	/**
	 * 登录名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 显示名称
	 */
	private String displayName;

	/**
	 * 资源对象集合
	 * 
	 */
	List<ResourceDetailsObject> resourceList;

	/**
	 * 基于安全认证时使用的缓存数组
	 */
	private GrantedAuthority[] grantedAuthority;

	/*
	 * 重写父类方法，返回一个当前登录用户允许访问的所有url的数组，用于安全认证
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
	 */
	public GrantedAuthority[] getAuthorities() {
		// 建立一个缓存，内容是当前用户可以访问的所有受保护的资源
		if (grantedAuthority == null) {
			// List<GrantedAuthority> grantedAuthorities = new
			// ArrayList<GrantedAuthority>();
			Map<String, GrantedAuthority> grantedAuthorityMap = new HashMap<String, GrantedAuthority>();

			Iterator<ResourceDetailsObject> resourceIte = getResourceList()
					.iterator();
			while (resourceIte.hasNext()) {
				ResourceDetailsObject resource = resourceIte.next();
				// 该资源是节点或者其他原因url为空，忽略
				if (resource.getResourceUrl() == null || 
						resource.getResourceUrl().trim().length() == 0)
					continue;

//				System.out.println("resourceId:" + resource.getResourceId() 
//						+ " resourceUrl:" + resource.getResourceUrl());
				
				// 过滤url相同的资源
				if (!grantedAuthorityMap.containsKey(resource.getResourceUrl())) {
					GrantedAuthority ga = new GrantedAuthorityImpl(resource
							.getResourceUrl());
					// grantedAuthorities.add();
					
					grantedAuthorityMap.put(resource.getResourceUrl(), ga);
				}
			}
			// 把得到的结果放到缓存
			grantedAuthority = grantedAuthorityMap.values().toArray(
					new GrantedAuthority[grantedAuthorityMap.size()]);

		}
		return grantedAuthority;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 得到拥有的资源列表
	 * 
	 * @return
	 */
	public List<ResourceDetailsObject> getResourceList() {
		return resourceList;
	}

	/**
	 * 设置拥有的资源列表
	 * 
	 * @param resourceList
	 */
	public void setResourceList(List<ResourceDetailsObject> resourceList) {
		this.resourceList = resourceList;
	}

	/**
	 * 设置用户名称
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 设置密码
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 得到用户id
	 * @return
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 得到显示名称
	 * @return
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 设置显示名称
	 * @param displayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}