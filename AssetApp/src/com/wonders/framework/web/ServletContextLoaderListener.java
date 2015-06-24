package com.wonders.framework.web;

import java.util.List;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wonders.framework.common.Constants;
import com.wonders.framework.core.service.OrganizationService;
import com.wonders.framework.util.ServiceProvider;

/**
 * 容器启动时初始化
 * 
 * @author liming.feng
 * 
 */
public class ServletContextLoaderListener extends ContextLoaderListener {

	private Logger logger = null;

	/**
	 * 容器启动时 取得所有受保护的资源列表，存在context中，key值是urlAuthorities， 供所有用户使用。
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		super.contextInitialized(servletContextEvent);
		logger = Logger.getLogger(this.getClass().getName());
		logger
				.info("===******* FrameworkApp ServletContextLoaderListener "
						+ "init...");

		try {
			initializeServiceProvider(servletContextEvent);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException(
					"Error: initialize service provider error!");
		}
		
		try {
			initializeMyBatis(servletContextEvent);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException(
					"Error: initialize mybatis error!");
		}

		/*try {
			loadAllResource(servletContextEvent);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException(
					"Error:load all resource from database error!");
		}*/
	}

	/**
	 * 容器关闭时 清除context中的urlAuthorities
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		servletContextEvent.getServletContext().removeAttribute(
				Constants.URL_AUTHORITIES);
		logger.info("*** FrameworkApp ServletContextLoaderListener"
				+ " contextDestroyed ***");
	}

	/**
	 * 读取所有的资源
	 * 
	 * @param servletContextEvent
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void loadAllResource(ServletContextEvent servletContextEvent)   
			throws Exception {
		List urlAuthorities = (List) servletContextEvent.getServletContext()
				.getAttribute(Constants.URL_AUTHORITIES);
		if (urlAuthorities == null) {
			/*
			 * ResourceDao resourceDao = (ResourceDao)
			 * SpringUtil.getSpringBean(servletContextEvent
			 * .getServletContext(), "resourceDao"); urlAuthorities =
			 * resourceDao.getAllResource();
			 */
			urlAuthorities = ServiceProvider.getService(
					OrganizationService.class).getAllResources();
			servletContextEvent.getServletContext().setAttribute(
					Constants.URL_AUTHORITIES, urlAuthorities);
		}
	}

	/**
	 * 初始化provider
	 * 
	 * @param servletContextEvent
	 * @throws Exception
	 */
	public static void initializeServiceProvider(
			ServletContextEvent servletContextEvent) throws Exception {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContextEvent
						.getServletContext());
		ServiceProvider.initialiaze(applicationContext);
	}
	
	
	/**
	 * 初始化mybatis
	 * 
	 * @param servletContextEvent
	 * @throws RuntimeException
	 */
	public static void initializeMyBatis(ServletContextEvent servletContextEvent) throws Exception{
		Class.forName("com.wonders.framework.util.MyBatisUtils");	
	}
}
