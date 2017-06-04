package com.taxsys.test;

import javax.persistence.*;
@Entity
@Table(name="product_inf")
public class Product
{
	// 定义标识属性
	@Id @Column(name="product_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer productId;
	private String name;

	// 无参数的构造器
	public Product(){}
	// 初始化全部属性的构造器
	public Product(String name)
	{
		this.name = name;
	}

	// productId的setter和getter方法
	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}
	public Integer getProductId()
	{
		return this.productId;
	}

	// name的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
}