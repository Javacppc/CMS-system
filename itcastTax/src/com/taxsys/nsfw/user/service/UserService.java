package com.taxsys.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.taxsys.core.exception.ServiceException;
import com.taxsys.nsfw.user.entity.User;
import com.taxsys.nsfw.user.entity.UserRole;

public interface UserService {
		/**
		 *  根据ID加载实体
		 * @param id
		 * @return
		 */
		User get(Serializable id);
		/**
		 *  保存实体
		 * @param entity
		 * @return
		 */
		Serializable save(User entity);
		/**
		 *  更新实体
		 * @param entity
		 */
		void update(User entity);
		/**
		 *  删除实体
		 * @param entity
		 */
		void delete(User entity);
		/**
		 *  根据ID删除实体，由於用戶的特殊性，所以在刪除用戶的同時也需要刪除
		 *  用戶所具有的角色
		 * @param id
		 */
		void delete(Serializable id);
		/**
		 *  获取所有实体
		 * @return
		 * @throws ServiceException
		 */
		List<User> findAll() throws ServiceException;
		/**
		 *  获取实体总数
		 * @return
		 */
		long findCount();
		User findById(Serializable id) throws ServiceException;
		void exportExcel(List<User> listUser, ServletOutputStream outputStream);
		void importExcel(File headImg, String headImgFileName);
		/**
		 * 根據用戶id和帳戶名查找
		 * @param id 用戶id
		 * @param account 用戶帳戶
		 * @return
		 */
		List<User> findUserByIdAndAccount(String id, String account);
		/**
		 * 自定義級聯保存--在保存用戶的時候同時保存角色
		 * @param user
		 * @param roleId
		 */
		void saveUserAndRole(User user, String...roleId);
		/**
		 * 更新的時候也進行級聯保存
		 * @param user
		 */
		void updateUserAndRole(User user, String...roleId);
		/**
		 * 通過用戶Id找到相應用戶所對應的角色集合
		 * @param id
		 * @return
		 */
		List<UserRole> findUserRoleByUserId(String id);
		List<User> findUserByAccountAndPass(String account, String password);
}
