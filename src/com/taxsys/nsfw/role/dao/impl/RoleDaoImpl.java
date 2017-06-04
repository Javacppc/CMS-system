package com.taxsys.nsfw.role.dao.impl;

import org.hibernate.Query;

import com.taxsys.core.dao.impl.BaseDaoImpl;
import com.taxsys.nsfw.role.dao.RoleDao;
import com.taxsys.nsfw.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
	/**
	 * 通過角色Id刪除其對應的權限
	 */
	@Override
	public void deleteRolePrivilegeByRoleId(String roleId) {
		Query query = getSessionFactory().getCurrentSession().createQuery("DELETE FROM RolePrivilege WHERE role.roleId=?");
		query.setParameter(0, roleId);
		query.executeUpdate();
	}

}
