package com.gor.sellphotos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.User;
import com.gor.sellphotos.repository.UserRepository;

/**
 *
 */
@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/rest/user")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@RequestParam("name") String name) {
		LOGGER.debug("loading user {}", name);
		User user = null;
		List<User> users = userRepository.findByName(name);
		if (!CollectionUtils.isEmpty(users)) {
			LOGGER.debug("creating user {}", name);
			user = new User();
			user.setName(name);
			user.setSchool("School for " + name);
			userRepository.save(user);
		} else {
			user = users.get(0);
		}
		LOGGER.debug("user {}", user);
		return user;
	}

}