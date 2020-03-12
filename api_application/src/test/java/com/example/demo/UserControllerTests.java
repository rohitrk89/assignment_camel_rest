package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.api.controller.User;
import com.example.api.controller.UserController;
import com.example.api.controller.UserService;
import com.example.api.exception.DataPersistenceException;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTests {

	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;

	List<User> usersList = new ArrayList<User>();

	
	void setUp() {
		User user = new User();
		user.setFirstName("Rani");
		user.setFirstName("Kothari");
		user.setFirstName("13-11-1990");
		usersList.add(user);

	}
	
	@Test
	void testAllUsers() {
		User user = new User();
		user.setFirstName("Rani");
		user.setLastName("Kothari");
		usersList.add(user);
		when(userService.getAllUsers()).thenReturn(usersList);
		
		ResponseEntity<List<User>> responseEntity = userController.getAllUsers();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	

	@Test
	void testAddUsers() throws DataPersistenceException {
		LocalDate date = LocalDate.now();
		User user = new User();
		user.setFirstName("Rani");
		user.setLastName("Kothari");
		user.setBirthDate(date);
		
		when(userService.addNewUser(user)).thenReturn(user);
		
		ResponseEntity<String> responseEntity = userController.addUsers(user);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	void getUserWithId() {
		LocalDate date = LocalDate.now();
		User user = new User();
		user.setFirstName("Rani");
		user.setLastName("Kothari");
		user.setBirthDate(date);
		when(userService.getUser(1)).thenReturn(user);
		
		ResponseEntity<User> responseEntity = userController.getUser(1);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
}
