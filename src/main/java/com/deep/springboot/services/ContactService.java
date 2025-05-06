package com.deep.springboot.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deep.springboot.entity.Contacts;
import com.deep.springboot.entity.User;
import com.deep.springboot.repository.ContactRepository;
import com.deep.springboot.repository.UserRepository;
@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private UserRepository repository;
	
	
	public Contacts addContact(Contacts contacts,int userId) {
		Contacts contact = contactRepository.save(contacts);
		User user = repository.findById(userId).get();
		List<Contacts> list = user.getContacts();
		list.add(contact);
		repository.save(user);
		return contactRepository.save(contacts);
	}

	public List<Contacts> getContacts(int id) {
		Optional<User> user = repository.findById(id);
		return user.get().getContacts();
	}

	public List<Contacts> sortAtZ() {
		List<Contacts> contact = contactRepository.findByQuery();
		return contact;
	}

	public List<Contacts> sortZtA() {
		List<Contacts> contact = contactRepository.findByQuery2();
		return contact;
	}

	public List<Contacts> searchContacts(String query) {
		return contactRepository.findByQuery3(query);
	}

	public Contacts update(Contacts contacts) {
		 return contactRepository.save(contacts);
	}

	public User delete(int userid,int conId) {
		User user = repository.findById(userid).get();
		List<Contacts> contacts = user.getContacts();
		Contacts con=null;
		for (Contacts contact : contacts) {
			if(contact.getId()==conId) {
				con=contact;
				break;
			}
		}
			if(con !=null){
			contacts.remove(con);
			}
		contactRepository.delete(con);
		repository.save(user);
		return user;
	}
	}





