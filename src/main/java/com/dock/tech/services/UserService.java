package com.dock.tech.services;

import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dock.tech.entities.User;
import com.dock.tech.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) throws AttributeNotFoundException {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new AttributeNotFoundException());
	}
	
	
}
