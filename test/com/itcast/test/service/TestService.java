package com.itcast.test.service;

import java.io.Serializable;

import com.itcast.test.entity.Person;

public interface TestService {
	public void say();
	/**
	 * 保存
	 * @param person
	 */
	public void save(Person person);
	/**
	 * 查詢
	 * @param id
	 * @return
	 */
	public Person findPerson(Serializable id);
}
