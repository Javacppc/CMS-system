package com.itcast.nsfw.user.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcast.nsfw.user.dao.UserDao;
import com.itcast.nsfw.user.entity.User;
import com.itcast.nsfw.user.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public User get(Serializable id) {
		return userDao.get(id);
	}

	@Override
	public Serializable save(User entity) {
		return userDao.save(entity);
	}

	@Override
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	public void delete(User entity) {
		userDao.delete(entity);
	}

	@Override
	public void delete(Serializable id) {
		userDao.delete(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public long findCount() {
		return userDao.findCount();
	}

	@Override
	public User findById(Serializable id) {
		return userDao.findById(id);
	}
	
}
