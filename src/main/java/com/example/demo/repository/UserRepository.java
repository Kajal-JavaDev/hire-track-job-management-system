package com.example.demo.repository;

import com.example.demo.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{//User means which type of entity it will use it. 
	User findByUsername(String username);

}
