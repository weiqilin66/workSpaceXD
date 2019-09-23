package com.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private RepositoryImpl repository;
	public void add() {
		System.out.println("userService add...");
		repository.save();
	}
}
