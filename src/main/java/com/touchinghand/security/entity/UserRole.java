package com.touchinghand.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRole implements Serializable{
	
	private static final long serialVersionUID = 283756576679812743L;
	
	@Id
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="user_name")
	private String userName;

	@Column(name="role_name")
	private String userRole;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
}
