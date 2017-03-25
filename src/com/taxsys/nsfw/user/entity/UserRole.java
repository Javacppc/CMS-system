package com.taxsys.nsfw.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taxsys.nsfw.role.entity.Role;
/**
 * UserRole表與Role表是單向的N-1關聯關係
 */
@Entity
@Table(name="user_role")
public class UserRole implements Serializable{
	@ManyToOne(targetEntity=Role.class)
	@JoinColumn(name="role_id", referencedColumnName="role_id")
	@Id
	private Role role;
	@Id
	@Column(name="user_id", length=32)
	private String userId;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UserRole(Role role, String userId) {
		this.role = role;
		this.userId = userId;
	}
	public UserRole() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserRole other = (UserRole) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
}
