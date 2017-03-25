package com.taxsys.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import com.taxsys.core.dao.impl.BaseDaoImpl;
import com.taxsys.nsfw.user.dao.UserDao;
import com.taxsys.nsfw.user.entity.User;
import com.taxsys.nsfw.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findUserByIdAndAccount(String id, String account) {
		String hql = "FROM User where account='"+account+"'";
		//在編輯頁面中需要解決這樣的情況:即排除自己的id號以防止系統以為填寫了相同的用戶名
		if (StringUtils.isNotBlank(id)) {
			hql += " AND id!='"+id+"'";
		}
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getSessionFactory().getCurrentSession().save(userRole);
	}

	@Override
	public void deleteUserRoleByUserId(Serializable id) {
		Query query = getSessionFactory().getCurrentSession().createQuery("DELETE FROM UserRole WHERE userId=?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findUserRoleByUserId(String id) {
		Query query = getSessionFactory().getCurrentSession().createQuery("FROM UserRole WHERE userId=?");
		query.setParameter(0, id);
		return query.list();
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		Query query = getSessionFactory().getCurrentSession().createQuery("FROM User WHERE account=? AND password=?");		
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}
	
}
