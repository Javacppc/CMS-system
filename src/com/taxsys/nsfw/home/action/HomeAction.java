package com.taxsys.nsfw.home.action;

import com.taxsys.core.action.BaseAction;
/**
 * 在系統首頁中嵌入納稅服務系統的頁面
 * @author zhuxiaodong
 *
 */
public class HomeAction extends BaseAction{
	/**
	 * 加載整個納稅服務首頁框架
	 * @return
	 */
	public String frame() {
		return "frame";
	}
	/**
	 * 加載左部菜單欄
	 * @return
	 */
	public String left() {
		return "left";
	}
	/**
	 * 加載頂部導航欄
	 * @return
	 */
	public String top() {
		return "top";
	}
}
