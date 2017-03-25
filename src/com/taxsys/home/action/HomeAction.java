package com.taxsys.home.action;

import com.taxsys.core.action.BaseAction;
/**
 * 跳轉到首頁的Action
 * @author zhuxiaodong
 *
 */
public class HomeAction extends BaseAction{
	@Override
	public String execute() throws Exception {
		return "home";
	}
}
