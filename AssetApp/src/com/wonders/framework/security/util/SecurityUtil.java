package com.wonders.framework.security.util;

import javax.servlet.ServletContext;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * security工具类
 * 
 * @author Downpour
 * 
 */
public class SecurityUtil {

	/**
	 * Returns the current user
	 * 
	 * @return
	 */
	public static UserDetails getCurrentUser() {
		return (UserDetails)SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
	}

	/**
	 * 得到spring bean
	 * 
	 * @param context
	 * @param beanName
	 * @return
	 */
	public Object getSpringBean(ServletContext context, String beanName) {
		return WebApplicationContextUtils.getWebApplicationContext(context)
				.getBean(beanName);
	}
}
