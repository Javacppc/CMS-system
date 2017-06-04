package com.itcast.nsfw.user.dao;

import java.util.List;

import com.itcast.core.dao.BaseDao;
import com.itcast.nsfw.user.entity.User;

public interface UserDao extends BaseDao<User> {
	/**
	 * 根據用戶id和帳戶查找
	 * @param id
	 * @param account
	 * @return
	 */
	List<User> findUserByIdAndAccount(String id, String account);

}
