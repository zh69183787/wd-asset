package com.wonders.framework.security.interceptor;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.intercept.web.FilterInvocation;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.util.AntUrlPathMatcher;
import org.springframework.security.util.RegexUrlPathMatcher;
import org.springframework.security.util.UrlMatcher;

import com.wonders.framework.common.Constants;
import com.wonders.framework.core.dto.ResourceDetailsObject;

/**
 * 资源访问拦截器
 * 
 * @author liming.feng
 * 
 */
public class SecureResourceFilterInvocationDefinitionSource implements
		FilterInvocationDefinitionSource, InitializingBean {

	/**
	 * url匹配器
	 */
	private UrlMatcher urlMatcher;

	/**
	 * 是否使用ant path
	 */
	private boolean useAntPath = true;

	/**
	 * 是否全部转成小写匹配
	 */
	private boolean lowercaseComparisons = true;

	/**
	 * @param useAntPath
	 *            the useAntPath to set
	 */
	public void setUseAntPath(boolean useAntPath) {
		this.useAntPath = useAntPath;
	}

	/**
	 * @param lowercaseComparisons
	 */
	public void setLowercaseComparisons(boolean lowercaseComparisons) {
		this.lowercaseComparisons = lowercaseComparisons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		// default url matcher will be RegexUrlPathMatcher
		this.urlMatcher = new RegexUrlPathMatcher();

		if (useAntPath) { // change the implementation if required
			this.urlMatcher = new AntUrlPathMatcher();
		}

		// Only change from the defaults if the attribute has been set
		if ("true".equals(lowercaseComparisons)) {
			if (!this.useAntPath) {
				((RegexUrlPathMatcher) this.urlMatcher)
						.setRequiresLowerCaseUrl(true);
			}
		} else if ("false".equals(lowercaseComparisons)) {
			if (this.useAntPath) {
				((AntUrlPathMatcher) this.urlMatcher)
						.setRequiresLowerCaseUrl(false);
			}
		}
	}

	/*
	 * 如果url受到保护，返回这个url (non-Javadoc)
	 * 
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#getAttributes(java.lang.Object)
	 */
	public ConfigAttributeDefinition getAttributes(Object filter)
			throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) filter;
		String requestURI = filterInvocation.getRequestUrl();

		// 得到所有受到保护的资源
		List<ResourceDetailsObject> urlAuthorities = this
				.getUrlAuthorities(filterInvocation);

		if (urlAuthorities == null) {
			return null;
		}

		// 把保护的资源跟requestURI匹配，看是否存在其中
		String grantedAuthorities = null;
		for (ResourceDetailsObject r : urlAuthorities) {
			String url = r.getResourceUrl();
			if (url == null) {
				continue;
			}
			if (requestURI.indexOf("?") != -1) {
				requestURI = requestURI.substring(0, requestURI.indexOf("?"));
			}
			if (urlMatcher.pathMatchesUrl(url, requestURI)) {
				grantedAuthorities = requestURI;
				break;
			}
		}

		// 如果RequestURI受到保护，返回RequestURI
		if (grantedAuthorities != null) {
			ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
			configAttrEditor.setAsText(grantedAuthorities);
			ConfigAttributeDefinition cd = (ConfigAttributeDefinition) configAttrEditor
					.getValue();
			return cd;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#getConfigAttributeDefinitions()
	 */
	@SuppressWarnings("unchecked")
	public Collection getConfigAttributeDefinitions() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.intercept.ObjectDefinitionSource#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return true;
	}

	/**
	 * 得到urlAuthorities, ServletContextLoaderListener初始化系统的时候放在ServletContext中
	 * 
	 * @param filterInvocation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ResourceDetailsObject> getUrlAuthorities(FilterInvocation filterInvocation) {
		ServletContext servletContext = filterInvocation.getHttpRequest()
				.getSession().getServletContext();
		return (List<ResourceDetailsObject>) servletContext
				.getAttribute(Constants.URL_AUTHORITIES);
	}

}