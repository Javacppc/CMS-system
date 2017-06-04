package com.itcast.nsfw.user.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import com.itcast.core.dao.impl.BaseDaoImpl;
import com.itcast.nsfw.user.dao.UserDao;
import com.itcast.nsfw.user.entity.User;

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
	
}
