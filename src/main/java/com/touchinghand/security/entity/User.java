package com.touchinghand.security.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable{

	private static final long serialVersionUID = 3411681657898339568L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="user_name")
	private String username;
	
	@Column(name="user_pass")
	private String password;
	
	@Column(name="salt")
	private String salt;
	
	@OneToMany(targetEntity=UserRole.class, fetch=FetchType.EAGER)
	@JoinColumn()
	private List<UserRole> roles;
	
	
	@Column(name="active")
	private Boolean active;
	
	
	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
