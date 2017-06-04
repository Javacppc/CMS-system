package com.taxsys.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="order_inf")
public class Order
{
	// 定义标识属性
	@Id @Column(name="order_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	private Date orderDate;
	// 关联的的订单项
	@OneToMany(targetEntity=OrderItem.class, mappedBy="order")
	private Set<OrderItem> items
		= new HashSet<>();

	// 无参数的构造器
	public Order(){}

	// 初始化全部成员变量的构造器
	public Order(Date orderDate)
	{
		this.orderDate = orderDate;
	}

	// orderId的setter和getter方法
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}
	public Integer getOrderId()
	{
		return this.orderId;
	}

	// orderDate的setter和getter方法
	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}
	public Date getOrderDate()
	{
		return this.orderDate;
	}

	// items的setter和getter方法
	public void setItems(Set<OrderItem> items)
	{
		this.items = items;
	}
	public Set<OrderItem> getItems()
	{
		return this.items;
	}
}