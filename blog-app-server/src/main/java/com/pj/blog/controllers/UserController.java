package com.pj.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pj.blog.dto.UserDto;
import com.pj.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto createUser(@RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
		UserDto updatedUser = this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteUser(@PathVariable("userId") Integer uid) {
		this.userService.deleteUser(uid);
		return "User deleted Successfully";
	}

	@GetMapping
	public List<UserDto>getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	public UserDto getSingleUser(@PathVariable Integer userId) {
		return userService.getUserById(userId);
	}

}