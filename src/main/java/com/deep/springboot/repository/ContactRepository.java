package com.deep.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deep.springboot.entity.Contacts;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Integer> {
	
	@Query(value = "SELECT contacts FROM Contacts contacts ORDER BY contacts.firstName")
	List<Contacts> findByQuery();
	
	@Query(value = "SELECT contacts FROM Contacts contacts ORDER BY contacts.firstName DESC")
	List<Contacts> findByQuery2();
	
	@Query(value = "SELECT con FROM Contacts con WHERE con.firstName LIKE %:query% OR con.lastName LIKE %:query% OR con.email LIKE %:query%")
	List<Contacts> findByQuery3(String query);

}