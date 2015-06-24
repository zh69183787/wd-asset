package com.wonders.framework.security.exception;

import org.springframework.security.AuthenticationException;

/**
 * 认证异常
 * 
 * @author cheney
 * 
 */
public class AuthCodeValidationException extends AuthenticationException {

	/**
	 * uid
	 */
	private static final long serialVersionUID = -3426248563816728565L;

	/**
	 * 构造函数
	 * 
	 * @param msg
	 */
	public AuthCodeValidationException(String msg) {
		super(msg);
	}
}
