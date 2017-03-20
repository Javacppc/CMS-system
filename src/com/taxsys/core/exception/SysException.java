package com.taxsys.core.exception;
/**
 * 自定義系統異常
 * @author zhuxiaodong
 *
 */
public abstract class SysException extends Exception{
	private String errorMsg;
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public SysException() {
		super();
	}

	public SysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorMsg = message;
	}

	public SysException(String message, Throwable cause) {
		super(message, cause);
		this.errorMsg = message;
	}

	public SysException(String message) {
		super(message);
		this.errorMsg = message;
	}

	public SysException(Throwable cause) {
		super(cause);
	}
	
}
