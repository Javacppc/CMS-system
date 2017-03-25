package com.taxsys.nsfw.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="user")
public class User implements Serializable {
	
	@Id
	@Column(name="user_id", length=32)
	@GenericGenerator(name="fk_uuid", strategy="uuid")
	@GeneratedValue(generator="fk_uuid")
	private String id;
	@Column(name="user_dept", length=20, nullable=false)
	private String dept;
	/**
	 * 帳號
	 */
	@Column(name="user_account", length=50, nullable=false)
	private String account;
	/**
	 * 用戶名
	 */
	@Column(name="user_name", length=20, nullable=false)
	private String name;
	@Column(name="user_password", length=50, nullable=false)
	private String password;
	/**
	 * 頭像地址
	 */
	@Column(name="user_head_img", length=100)
	private String headImg;
	@Column(name="user_gender")
	private boolean gender;
	@Column(name="user_state", length=1)
	private String state;
	@Column(name="user_mobile", length=20)
	private String mobile;
	@Column(name="user_email", length=50)
	private String email;
	@Column(name="user_birthday", length=10)
	private Date birthday;//2017-3-11
	/**
	 * 備註
	 */
	@Column(name="user_memo", length=200)
	private String memo;
	/**
	 * 不將該屬性映射到數據庫表中
	 */
	@Transient
	private List<UserRole> userRoles;
	/**
	 * 用戶狀態
	 */
	public static String USER_STATE_VALID = "1";
	public static String USER_STATE_INVALID = "0";
	

	
	public User() {
	}
	public User(String id, String dept, String account, String name, String password, String headImg, boolean gender,
			String state, String mobile, String email, Date birthday, String memo) {
		super();
		this.id = id;
		this.dept = dept;
		this.account = account;
		this.name = name;
		this.password = password;
		this.headImg = headImg;
		this.gender = gender;
		this.state = state;
		this.mobile = mobile;
		this.email = email;
		this.birthday = birthday;
		this.memo = memo;
	}
	
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
