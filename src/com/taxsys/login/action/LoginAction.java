package com.taxsys.login.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.taxsys.core.action.BaseAction;
import com.taxsys.core.constant.Constant;
import com.taxsys.nsfw.user.entity.User;
import com.taxsys.nsfw.user.service.UserService;
/**
 * 當還未登陸的用戶訪問系統時需要先登錄
 * @author zhuxiaodong
 *
 */
public class LoginAction extends BaseAction{
	/**
	 * 接受登陸請求的數據
	 */
	private User user;
	/**
	 * 若用戶沒有填寫用戶名和密碼那麼在這裡需要將此信息傳遞給用戶
	 */
	private String loginResult;
	@Autowired
	private UserService userService;
	/**
	 * 跳轉到登陸頁面
	 * @return
	 */
	public String toLoginUI() {
		return "loginUI";
	}
	/**
	 * 跳轉到沒有權限訪問系統功能提示頁面
	 * @return
	 */
	public String toNoPermissionUI() {
		return "noPermissionUI";
	}
	
	/**
	 * 處理登陸的Action
	 * @return
	 */
	public String login() {
		if (user != null) {
			if (StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())) {
				//根據用戶名和密碼查詢用戶列表
				List<User> userList = userService.findUserByAccountAndPass(user.getAccount(), user.getPassword());
				if (userList != null && userList.size() > 0) {
					//2.1 登陸成功
					User user = userList.get(0);
					//根据用户id查询该用户的所有角色（這樣在權限檢查的時候就不需要每次在用戶點擊URI的時候查詢數據庫了）
					//而是直接從保存在Session中的User對象取出相對應的角色列表就行了
					user.setUserRoles(userService.findUserRoleByUserId(user.getId()));
					
					//將用戶信息保存到session中
					ServletActionContext.getRequest().getSession().setAttribute(Constant.SYS_USER, user);
					//將用戶登錄記錄保存到日誌文件中
					Log log = LogFactory.getLog(getClass());
					log.info(new Date() + ":用戶" + user.getName() + "成功登入進入系統");
					//重定向到首頁
					return "homepage";
				} else {
					//登錄失敗
					loginResult = "用戶名或密碼錯誤!";
				}
			} else {
				loginResult = "帳戶或密碼不能為空！";
			}
		} else {
			loginResult = "請填寫您的用戶名和密碼！";
		}
		return toLoginUI();
	}
	/**
	 * 註銷登錄
	 * @return
	 */
	public String logout() {
		//清除Session中保存的用戶信息
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.SYS_USER);
		return "loginUI";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
