package com.taxsys.core.exception;

public class ActionException extends SysException{

	public ActionException() {
		super("請求發生錯誤！");
	}

	public ActionException(String message) {
		super(message);
	}
	
}
