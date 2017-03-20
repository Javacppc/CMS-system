package com.taxsys.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="tax_person")
public class Person implements Serializable {
	@Id
	//該主鍵生成器的名字是fk_uuid  使用Hibernate的uuid策略
	@GenericGenerator(name="fk_uuid", strategy="uuid")
	//使用fk_uuid主鍵生成器
	@GeneratedValue(generator="fk_uuid")
	@Column(length=32, name="person_id")
	private String id;
	//該列不能為空值
	@Column(name="person_name", length=20, nullable=false)
	private String name;
	public Person() {
		
	}
	public Person(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Person(String name) {
		super();
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "納稅人：" + name;
	}
	
}
