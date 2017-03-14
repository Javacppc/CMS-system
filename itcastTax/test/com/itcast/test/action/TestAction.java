package com.itcast.test.action;

import javax.annotation.Resource;

import com.itcast.test.service.TestService;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{
	/**
	 * 依賴注入
	 */
	@Resource
	private TestService testService;
	
	@Override
	public String execute() throws Exception {
		testService.say();
		return SUCCESS;
	}
}
