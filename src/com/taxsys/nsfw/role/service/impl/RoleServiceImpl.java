package com.taxsys.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxsys.core.exception.ServiceException;
import com.taxsys.nsfw.role.dao.RoleDao;
import com.taxsys.nsfw.role.entity.Role;
import com.taxsys.nsfw.role.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;
	@Override
	public Role get(Serializable id) {
		return roleDao.get(id);
	}

	@Override
	public Serializable save(Role entity) {
		return roleDao.save(entity);
	}
	/**
	 * 在更新頁面中我們需要先刪除之前的權限再加載修改後的權限
	 */
	@Override
	public void update(Role role) {
		//1.刪除該角色對應的所有權限
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		//2.更新角色及其權限
		roleDao.update(role);
	}

	@Override
	public void delete(Role entity) {
		//刪除角色對應的權限
		roleDao.deleteRolePrivilegeByRoleId(entity.getRoleId());
		//刪除角色
		roleDao.delete(entity);
	}

	@Override
	public void delete(Serializable id) {
		//刪除角色對應的權限
		roleDao.deleteRolePrivilegeByRoleId(id);
		//刪除角色
		roleDao.delete(id);
	}

	@Override
	public List<Role> findAll() throws ServiceException {
		return roleDao.findAll();
	}

	@Override
	public long findCount() {
		return roleDao.findCount();
	}

	@Override
	public Role findById(Serializable id) throws ServiceException {
		return roleDao.findById(id);
	}
}
