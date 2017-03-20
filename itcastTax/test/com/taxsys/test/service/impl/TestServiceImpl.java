package com.taxsys.test.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;*/

import com.taxsys.test.dao.TestDao;
import com.taxsys.test.entity.Person;
import com.taxsys.test.service.TestService;
@Service("testService")
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestDao testDao;
	@Override
	//@Transactional(readOnly=true)
	public void say() {
		System.out.println("Service saying hi.");
	}
	@Override
	//rollbackFor代表必須回滾（無論是檢查型異常還是unchecked異常）
	//@Transactional(isolation=Isolation.DEFAULT, timeout=5, propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public void save(Person person) {
		testDao.save(person);
	}
	@Override
	//@Transactional(readOnly=true)
	public Person findPerson(Serializable id) {
		//測試時用，只讀事務中不可能有更新操作，負責數據不會插進去
		//testDao.save(new Person("瞿珉鈺"));
		return testDao.findById(id);
	}
}
