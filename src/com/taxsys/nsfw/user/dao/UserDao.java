package com.taxsys.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import com.taxsys.core.dao.BaseDao;
import com.taxsys.nsfw.user.entity.User;
import com.taxsys.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {
	/**
	 * 根據用戶id和帳戶查找
	 * @param id
	 * @param account
	 * @return
	 */
	List<User> findUserByIdAndAccount(String id, String account);

	void saveUserRole(UserRole userRole);
	
	void deleteUserRoleByUserId(Serializable id);

	List<UserRole> findUserRoleByUserId(String id);
	/**
	 * 根據帳戶和密碼查詢登錄用戶是否存在于數據庫之中
	 * @param account
	 * @param password
	 * @return
	 */
	List<User> findUserByAccountAndPass(String account, String password);

}
