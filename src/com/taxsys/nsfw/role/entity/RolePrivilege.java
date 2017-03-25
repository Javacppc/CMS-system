package com.taxsys.nsfw.role.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色權限表
 * 是N的一方
 * 角色ID以及權限code
 * @author zhuxiaodong
 *
 */
@Entity
@Table(name="role_privilege")
public class RolePrivilege implements Serializable{
	/**
	 * 角色權限表的邏輯主鍵值
	 */
	@Column(name="role_privilege_id", length=32)
	@GenericGenerator(name="pk_uuid", strategy="uuid")
	@GeneratedValue(generator="pk_uuid")
	@Id
	private String id;
	
	/**
	 * 禁止延遲加載
	 * 
	 */
	@ManyToOne(targetEntity=Role.class, fetch=FetchType.EAGER)
	@JoinColumn(name="role_id", referencedColumnName="role_id")
	private Role role;
	/**
	 * 權限碼
	 */
	private String code;
	
	public RolePrivilege(Role role, String code) {
		super();
		this.role = role;
		this.code = code;
	}

	public RolePrivilege() {
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePrivilege other = (RolePrivilege) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
}
