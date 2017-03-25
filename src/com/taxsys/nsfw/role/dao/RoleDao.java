package com.taxsys.nsfw.role.dao;

import java.io.Serializable;

import com.taxsys.core.dao.BaseDao;
import com.taxsys.nsfw.role.entity.Role;

public interface RoleDao extends BaseDao<Role>{

	void deleteRolePrivilegeByRoleId(Serializable roleId);

}
