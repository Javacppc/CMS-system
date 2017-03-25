package com.taxsys.nsfw.role.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

/**
 * 角色表
 * 角色ID, 角色名稱，狀態
 * 角色和角色權限表是一對多的關係
 * 1-N的關聯關係
 * 角色表是1的一方
 * 角色表示主表
 * @author zhuxiaodong
 *
 */
@Entity
@Table(name="role")
public class Role implements Serializable{
	/**
	 * 角色ID
	 */
	@Id
	@GenericGenerator(name="pk_uuid", strategy="uuid")
	@GeneratedValue(generator="pk_uuid")
	@Column(name="role_id")
	private String roleId;
	/**
	 * 角色名稱
	 */
	@Column(name="role_name",length=32, nullable=false)
	private String name;
	/**
	 * 狀態
	 */
	@Column(name="state", length=1)
	private String state;
	//因為在editUI頁面顯示權限信息的時候需要查看到權限信息，所以這些權限信息不能進行懶加載
	//否則到時就會出錯！
	/*@OneToMany(targetEntity=RolePrivilege.class, 
		    mappedBy="role", cascade=CascadeType.ALL,
		    fetch=FetchType.EAGER)*/
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "role")
	@Cascade(value={CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.SELECT)
	private Set<RolePrivilege> rolePrivileges = new HashSet<>();
	
	/**
	 * 角色狀態
	 */
	public static String ROLE_STATE_VALID = "1";
	public static String ROLE_STATE_INVALID = "0";
	
	
	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}
	public void setRolePrivileges(Set<RolePrivilege> rolePrivilege) {
		this.rolePrivileges = rolePrivilege;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Role() {
	}
	public Role(String roleId, String roleName, String state, Set<RolePrivilege> rolePrivilege) {
		super();
		this.roleId = roleId;
		this.name = roleName;
		this.state = state;
		this.rolePrivileges = rolePrivilege;
	}
	public Role(String roleId) {
		this.roleId = roleId;
	}
}
