package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public List<User> findByName(String name);

}
