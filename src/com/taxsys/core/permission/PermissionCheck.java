package com.taxsys.core.permission;

import com.taxsys.nsfw.user.entity.User;

public interface PermissionCheck {
	/**
	 * 用於判斷某個用戶是否具有特定系統標誌符對應的權限
	 * @param user 用戶信息
	 * @param string 系統訪問的標誌符
	 * @return 返回true表示有權限，false表示沒有權限
	 */
	public boolean isAccessible(User user, String string);
	
}
