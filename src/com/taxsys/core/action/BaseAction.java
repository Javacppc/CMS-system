package com.taxsys.core.action;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 這裡定義了一個全局的Action基類
 * 用於抽取本系統中所有Action所公有的操作
 * 比如說：批量刪除在用戶管理，角色管理中都會用到，所以把它們抽象到這個
 * 公共Action之中，方便重用，無需寫多次了
 * @author zhuxiaodong
 *
 */
public abstract class BaseAction extends ActionSupport {
	/**
	 * 用於批量刪除時用
	 */
	protected String[] selectedRow;
	
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
}
