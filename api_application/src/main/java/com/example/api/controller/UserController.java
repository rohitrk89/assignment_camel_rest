package com.example.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.exception.DataPersistenceException;
import com.example.api.exception.RestErrorConstants;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(path = "/addUser")
	public ResponseEntity<String> addUsers(@Valid @RequestBody User user) throws DataPersistenceException {
			User savedUser = userService.addNewUser(user);
			if(savedUser==null) {
				throw new DataPersistenceException(RestErrorConstants.DATA_NOT_SAVED_SUCCESSFULLY);
			}
			return ResponseEntity.ok("User Data saved successfully");
	}

	@GetMapping(path = "/getUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping(path = "/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id) {
		return ResponseEntity.ok(userService.getUser(id));
	}

	@GetMapping(path = "/")
	public List<User> getMatchedUsers(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		return userService.getAllUsersWithSameName(firstName, lastName);
	}
}
