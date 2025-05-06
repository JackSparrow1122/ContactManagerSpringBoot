package com.deep.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deep.springboot.entity.Contacts;
import com.deep.springboot.entity.User;
import com.deep.springboot.services.ContactService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*")
public class ContactsController {
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping(path = "/contact/{userId}")
	protected Contacts addContact(@PathVariable int userId,@RequestBody Contacts contacts ) {
		return contactService.addContact(contacts,userId);
	}
	
	@PutMapping(path = "/update")
	protected Contacts update(@RequestBody Contacts contacts ) {
		return contactService.update(contacts);
	}
	
	@DeleteMapping(path = "/delete/{userId},{conId}")
	protected User delete(@PathVariable int userId ,@PathVariable int conId) {
		return contactService.delete(userId,conId);
	}
	
	
	@GetMapping(path = "/contacts/{id}")
	protected List<Contacts> getContacts(@PathVariable int id) {
		return contactService.getContacts(id);
	}
	
	@GetMapping(path = "/contactAtoZ")
	protected List<Contacts> SortAtZ() {
		return contactService.sortAtZ();
	}
	
	@GetMapping(path = "/contactZtoA")
	protected List<Contacts> SortZtoA() {
		return contactService.sortZtA();
	}
	
	@GetMapping(path="/serch")
	protected List<Contacts>  serch(@RequestParam String query) {
		return contactService.searchContacts(query);
	}
}

