package com.gor.sellphotos.repository;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByName(String name);

}
