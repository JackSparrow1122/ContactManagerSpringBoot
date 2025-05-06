package com.deep.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deep.springboot.entity.Contacts;
import com.deep.springboot.entity.User;
import com.deep.springboot.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) {
		user.setContacts(new ArrayList<Contacts>());
		return userRepository.save(user);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User deleteUser(int id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
		return user.get();
		}
		else
			return null;
	}

	public User auth(String email, String password) {
		Optional<User> user = userRepository.findByEmailAndPassword(email, password);
		if(user.isPresent()) {
			if(user.get().getEmail().equals(email) && user.get().getPassword().equals(password)) {
				return user.get();
			}
		}
		return null;

}
}
