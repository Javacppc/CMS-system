package com.itcast.nsfw.user.service;

import java.io.Serializable;
import java.util.List;

import com.itcast.nsfw.user.entity.User;

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
}
