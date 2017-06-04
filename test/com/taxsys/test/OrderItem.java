package com.taxsys.test;

import javax.persistence.*;
@Entity
@Table(name="order_item_inf")
public class OrderItem
	implements java.io.Serializable
{
	// 下面3个属性将作为联合主键
	// 定义关联的Order实体
	@ManyToOne(targetEntity=Order.class)
	// 映射名为order_id的外键列，参照order_inf的order_id主键列
	@JoinColumn(name="order_id" , referencedColumnName="order_id")
	@Id
	private Order order;
	// 定义关联的Product实体
	@ManyToOne(targetEntity=Product.class)
	// 映射名为product_id的外键列，参照product_inf的product_id主键列
	@JoinColumn(name="product_id" , referencedColumnName="product_id")
	@Id
	private Product product;
	// 该订单项订购的产品数量
	@Id
	private int count;

	// 无参数的构造器
	public OrderItem(){ }
	// 初始化全部成员变量的构造器
	public OrderItem(Order order , Product product , int count)
	{
		this.order = order;
		this.product = product;
		this.count = count;
	}

	// order的setter和getter方法
	public void setOrder(Order order)
	{
		this.order = order;
	}
	public Order getOrder()
	{
		return this.order;
	}

	// product的setter和getter方法
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public Product getProduct()
	{
		return this.product;
	}

	// count的setter和getter方法
	public void setCount(int count)
	{
		this.count = count;
	}
	public int getCount()
	{
		return this.count;
	}
	// 重写equals()方法，根据product、order、count判断是否相等
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(obj != null && obj.getClass() == OrderItem.class)
		{
			OrderItem target = (OrderItem)obj;
			return this.order.equals(target.getOrder())
				&& this.product.equals(target.getProduct())
				&& this.count == target.getCount();
		}
		return false;
	}
	// 重写hashCode()方法，根据product、order、count计算hashCode值
	public int hashCode()
	{
		return (this.product == null ? 0 : this.product.hashCode()) * 31 * 31
			+ (this.order == null ? 0 : this.order.hashCode()) * 31
			+ this.count;
	}
}