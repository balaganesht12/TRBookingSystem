package com.source.service;

import java.util.List;

import com.source.entity.User;

public interface UserService {

	public User addUser(User user);
	
	public User updateUser(User user);
	
	public User retrieveUserById(Long userId);
	
	public void deleteUserById(Long userId);
	
	public List<User> retrieveUserAll();
	
}
