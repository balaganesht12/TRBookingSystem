package com.source.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.source.entity.User;
import com.source.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@PostMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@GetMapping("/retrieveUserById")
	public User retrieveUserById(@RequestParam Long userId) {
		return userService.retrieveUserById(userId);
	}
	
	@GetMapping("/retrieveUserAll")
	public List<User> retrieveUserAll() {
		return userService.retrieveUserAll();
	}
	
	@DeleteMapping("/deleteUserById")
	public void deleteUserById(@RequestParam Long userId) {
		userService.deleteUserById(userId);
	}

}
