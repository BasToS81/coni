package com.gor.sellphotos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserDto {
	
	private String name;
	
	private List<String> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRole() {
		return roles;
	}

	public void setRole(List<String> role) {
		this.roles = role;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", roles=" + roles + "]";
	}

	public void addRole(String authority) {
		if (roles==null) {
			roles = new ArrayList<>();
		}
		roles.add(authority);
	}
	
}
