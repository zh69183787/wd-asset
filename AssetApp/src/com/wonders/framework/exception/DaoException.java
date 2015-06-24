package com.wonders.framework.exception;

/**
 * 
 * 封装Dao异常信息类
 * 
 * @author zhangdingsheng
 * 
 */
@SuppressWarnings("serial")
public class DaoException extends Exception {

	/**
	 * 错误消息
	 */
	private String errorMessage;

	/**
	 * 构造函数
	 * 
	 * @param errorMessage
	 */
	public DaoException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 得到错误消息
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

}
