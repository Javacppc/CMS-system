package com.taxsys.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.taxsys.nsfw.user.entity.User;

public interface UserService {
	// 根据ID加载实体
		User get(Serializable id);
		// 保存实体
		Serializable save(User entity);
		// 更新实体
		void update(User entity);
		// 删除实体
		void delete(User entity);
		// 根据ID删除实体
		void delete(Serializable id);
		// 获取所有实体
		List<User> findAll();
		// 获取实体总数
		long findCount();
		User findById(Serializable id);
		void exportExcel(List<User> listUser, ServletOutputStream outputStream);
		void importExcel(File headImg, String headImgFileName);
		/**
		 * 根據用戶id和帳戶名查找
		 * @param id 用戶id
		 * @param account 用戶帳戶
		 * @return
		 */
		List<User> findUserByIdAndAccount(String id, String account);
}
