package com.taxsys.core.permission.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.taxsys.core.permission.PermissionCheck;
import com.taxsys.nsfw.role.entity.Role;
import com.taxsys.nsfw.role.entity.RolePrivilege;
import com.taxsys.nsfw.user.entity.User;
import com.taxsys.nsfw.user.entity.UserRole;
import com.taxsys.nsfw.user.service.UserService;
/**
 * 權限檢查實現類
 * @author zhuxiaodong
 *
 */
public class PermissionCheckImpl implements PermissionCheck{
	
	@Autowired
	private UserService userService;
	@Override
	public boolean isAccessible(User user, String code) {
		//從Session中取出用戶，并取出用戶對應的角色信息
		List<UserRole> roleList = user.getUserRoles();
		//一般都是在登錄的是否就已經查詢到了用戶的所有信息并記錄在Session中
		//這段代碼只是爲了以防萬一（99%不會執行到）
		if (roleList == null) {
			roleList = userService.findUserRoleByUserId(user.getId());
		}
		//如果角色列表不為空
		if (roleList != null && roleList.size() > 0) {
			for (UserRole role : roleList) {
				Role r = role.getRole();
				for (RolePrivilege rp : r.getRolePrivileges()) {
					if (code.equals(rp.getCode())) {
						//有相應的訪問權限則直接放行
						return true;
					}
				}
			}
		}
		return false;
	}

}
