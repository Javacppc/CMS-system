package com.taxsys.core.exception;

public class ServiceException extends SysException{

	public ServiceException() {
		super("業務操作錯誤！");
	}

	public ServiceException(String message) {
		super(message);
	}
	
}
