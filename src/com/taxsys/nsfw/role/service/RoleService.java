package com.taxsys.nsfw.role.service;

import java.io.Serializable;
import java.util.List;

import com.taxsys.core.exception.ServiceException;
import com.taxsys.nsfw.role.entity.Role;

public interface RoleService {
	// 根据ID加载实体
	Role get(Serializable id);
	// 保存实体
	Serializable save(Role entity);
	// 更新实体
	void update(Role entity);
	// 删除实体
	void delete(Role entity);
	// 根据ID删除实体
	void delete(Serializable id);
	// 获取所有实体
	List<Role> findAll() throws ServiceException;
	// 获取实体总数
	long findCount();
	Role findById(Serializable id) throws ServiceException;
	
}
