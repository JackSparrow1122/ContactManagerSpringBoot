package com.deep.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deep.springboot.entity.User;
import com.deep.springboot.services.UserService;

import jakarta.servlet.http.HttpSession;
@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/user")
	protected User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@GetMapping(path ="/user")
	protected List<User> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping(path = "/auth")
	protected String authentication(@RequestBody User user ,HttpSession httpSession) {
		String email = user.getEmail(); String password = user.getPassword();
		User auth = userService.auth(email,password);
		if(auth != null)
			return "ok";
		
		else
			return "Invalid Email OR Password";
	}
	
	@DeleteMapping(path ="/deleteUser/{id}")
	protected User deleteUser(@PathVariable int id) {
		User users = userService.deleteUser(id);
		 if(users!=null)
			 return users;
		 else 
			 return null;
	}
}
