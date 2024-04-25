package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.models.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;

import org.springframework.dao.DataIntegrityViolationException;

@Service
public class userService {
	@Autowired
	private userDao userDao;
	
	public List<User> getUsers(){
		return this.userDao.getAllUser();
	}
	
	public User addUser(User user) {
//		return this.userDao.saveUser(user);
//		try {
//			return this.userDao.saveUser(user);
//		} catch (DataIntegrityViolationException e) {
//			// handle unique constraint violation, e.g., by throwing a custom exception
//
//			throw new RuntimeException("Add user error");
//		}
		if (checkUserExists(user.getUsername())) {
			throw new IllegalArgumentException("error iwth " + user.getUsername() + " already exists");
		}
		return this.userDao.saveUser(user);
	}

	public User checkLogin(String username,String password) {
		return this.userDao.getUser(username, password);
	}

	public boolean checkUserExists(String username) {
		return this.userDao.userExists(username);
	}
}
