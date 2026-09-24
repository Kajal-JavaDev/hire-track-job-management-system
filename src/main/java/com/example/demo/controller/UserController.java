package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.dto.LoginRequest;

import com.example.demo.service.JwtService;

import com.example.demo.dto.UserResponseDTO;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register")
	public UserResponseDTO registerUser(@RequestBody User user) {
		user.setRole("USER");
		User savedUser = userService.saveUser(user);
		UserResponseDTO response = new UserResponseDTO();
		response.setUsername(savedUser.getUsername());
		response.setRole(savedUser.getRole());
		return response;
		//return userService.saveUser(user);
	}
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) {
		User user = userService.findByUsername(loginRequest.getUsername());
		
		if(user != null && userService.checkPassword(loginRequest.getPassword(),user.getPassword())){
	//	return "Login Successful";
			return jwtService.generateToken(user.getUsername());
		}
		
	return "Invalid username or password";
}

}
