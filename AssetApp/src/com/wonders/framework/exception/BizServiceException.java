package com.wonders.framework.exception;

import java.util.List;

/**
 * 
 * 封装业务异常信息类
 * 
 * @author cheney
 * 
 */
@SuppressWarnings("unchecked")
public class BizServiceException extends Exception {

	/**
	 * 错误信息
	 */
	private String errorMessage;

	
	/**
	 * 构造函数
	 * 
	 * @param errorMessage
	 */
	public BizServiceException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
		
	/**
	 * 得到错误信息
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}	

}
