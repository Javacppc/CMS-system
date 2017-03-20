package com.taxsys.core.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;
/**
 * 我們通常想在捕獲到異常后做一些事情（即對異常的處理---比如發生異常之後跳轉到新的頁面）
 * ,做這些事情時可能需要之前的一些參數
 * @author zhuxiaodong
 *
 */
public class SysResultAction extends StrutsResultSupport{
	@Override
	protected void doExecute(String info, ActionInvocation invocation) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BaseAction action = (BaseAction) invocation.getAction();
		
		//做一些處理 do something。。。
		System.out.println("進入了SysResultAction...");
		//response.sendRedirect(req.getContextPath()+"/user_listUI.action");
		
	}
}
