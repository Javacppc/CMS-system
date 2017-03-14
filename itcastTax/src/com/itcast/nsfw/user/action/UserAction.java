package com.itcast.nsfw.user.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.itcast.nsfw.user.entity.User;
import com.itcast.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	@Autowired
	private UserService userService;
	/**
	 * 用於列出用戶信息的列表
	 */
	private List<User> userList;
	/**
	 * 用於接收用戶的輸入
	 */
	private User user;
	
	private String[] selectedRow;
	
	
	
	
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//列表頁面
	public String listUI() {
		userList = userService.findAll();
		return "listUI";
	}
	//跳轉到新增頁面
	public String addUI() {
		return "addUI";
	}
	//保存新增
	public String add() {
		if (user != null) {
			userService.save(user);
		}
		return "list";
	}
	//跳轉到編輯頁面
	public String editUI() {
		if (user != null && user.getId() != null) 
			user = userService.findById(user.getId());
		return "editUI";
	}
	//保存編輯
	public String edit() {
		if (user != null) {
			userService.update(user);
		}
		return "list";
	}
	//刪除
	public String delete() {
		if (user != null && user.getId() != null) {
			userService.delete(user.getId());
		}
		return "list";
	}
	//批量刪除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				userService.delete(id);
			}
		}
		return "list";
	}
}
