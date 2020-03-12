package com.example.api.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.api.exception.PropertyNotExpectedException;
import com.example.api.exception.RestErrorConstants;
import com.example.api.exception.UserNotFoundException;
import com.example.api.util.Util;

@Service
public class UserService {

	List<User> users = new ArrayList<User>();
	Integer userId = 0;
	
	/**This method is used to add new users.
	 * @param user
	 * @return
	 */
	public User addNewUser(User user) {
		if(user.getId() != null) {
			throw new PropertyNotExpectedException(RestErrorConstants.ID_PROPERTY_NOT_REQUIRED);
		}
		LocalDate currentDate = LocalDate.now();
		if(user.getBirthDate().isAfter(currentDate)) {
			throw new PropertyNotExpectedException(RestErrorConstants.NOT_BORN);
		}
		user.setId(++userId);
		users.add(user);
		return user;
	}
	
	/**This method is used to get all the users.
	 * @return
	 */
	public List<User> getAllUsers() {
		if(users.isEmpty()) {
			throw new UserNotFoundException(RestErrorConstants.NO_USERS_FOUND);
		}
		setUserAge(users);
		return users;
	}
	
	/**This method is used to get the user with the id specified.
	 * @param id
	 * @return
	 */
	public User getUser(int id) {
		Optional<User> data =  users.stream().filter(userData -> userData.getId() == id).findFirst();
		if(!data.isPresent()) {
				throw new UserNotFoundException(RestErrorConstants.USER_NOT_FOUND);
		}
		User user = data.get();
		user.setAge(Util.calculateAge(user.getBirthDate()));
		return user;
		
	}
	
	/**This method is used to get all the users that match the arguments.
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public List<User> getAllUsersWithSameName(String firstName , String lastName){
		List<User> listOfMatchedUsers =  users.stream().filter(user -> user.getFirstName().equalsIgnoreCase(firstName) && user.getLastName().equalsIgnoreCase(lastName)).collect(Collectors.toList());
		if(listOfMatchedUsers.isEmpty()) {
			throw new UserNotFoundException(RestErrorConstants.NO_USERS_FOUND);
		}
		setUserAge(listOfMatchedUsers);
		return listOfMatchedUsers; 
	}

	/**This method is used to the calculate the users age if not present!
	 * @param userList
	 */
	private void setUserAge(List<User> userList) {
		for (User user : userList) {
			if(user.getAge() <= 0) {
				user.setAge(Util.calculateAge(user.getBirthDate()));	
			}
			
		}
	}
}
