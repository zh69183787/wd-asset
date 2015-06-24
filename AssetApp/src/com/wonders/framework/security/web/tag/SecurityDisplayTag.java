package com.wonders.framework.security.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.security.context.SecurityContextHolder;

import com.wonders.framework.core.dto.ResourceDetailsObject;
import com.wonders.framework.core.dto.UserDetailsObject;

/**
 * 根据授权情况判断是否显示jsp控件的标签
 * 
 * @author liming.feng
 * 
 */
public class SecurityDisplayTag extends BodyTagSupport {

	/**
	 * uid
	 */
	private static final long serialVersionUID = 4483361655253585796L;

	/**
	 * 控件中使用的url的id
	 */
	private Integer urlId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		try {
			// 如果url置空，允许显示
			if (urlId == null) {
				return EVAL_BODY_INCLUDE;
			}

			// 从用户信息中取出全部已授权的url
			UserDetailsObject user = (UserDetailsObject) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			for (ResourceDetailsObject resource : user.getResourceList()) {
				if (urlId.equals(resource.getResourceId())) {
					return EVAL_BODY_INCLUDE;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e.toString());
		}
		return SKIP_BODY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * @return
	 */
	public Integer getUrlId() {
		return urlId;
	}

	/**
	 * @param urlId
	 */
	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}
}
