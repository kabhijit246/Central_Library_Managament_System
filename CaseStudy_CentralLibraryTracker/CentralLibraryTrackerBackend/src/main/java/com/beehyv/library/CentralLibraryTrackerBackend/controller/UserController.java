package com.beehyv.library.CentralLibraryTrackerBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Users;
import com.beehyv.library.CentralLibraryTrackerBackend.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register") // method for user registration in which details of user are added to the db by post mapping
	public Users addUsers(@RequestBody Users u) throws Exception {
		String tempEmail = u.getEmail();
		String tempUsername = u.getUsername();
		if(tempEmail != null && !"".equals(tempEmail)) {
			//fetching user details by their email if present
			Users userObj = userService.getUserByEmail(tempEmail);
			if(userObj != null) {
				//if present, then it will throw exception
				throw new Exception("*User with this email "+ tempEmail +" already exists.");
			}
		}
		if(tempUsername != null && !"".equals(tempUsername)) {
			//fetching user details by their username if present
			Users userObj = userService.getUserByUsername(tempUsername);
			if(userObj != null) {
				//if present, it will throw exception
				throw new Exception("*User with this username "+ tempUsername +" already exists. Try another!");
			}
		}
		Users userObj = null;
		//else, user details will be added to db and registration will be successful
		userObj = userService.addUsers(u);
		return userObj;
	}

	@PostMapping("/login") //method for login of users only after inputed username, password will be available in db
	public Users loginUser(@RequestBody Users u) throws Exception {
		String tempUsername = u.getUsername();
		String tempPassword = u.getPassword();
		Users userObj = null;
		String role = "Librarian";

		if(tempUsername != null && !"".equals(tempUsername) && tempPassword != null && !"".equals(tempPassword)) {
			//fetching user details using username and password
			userObj = userService.getUserByUsernameAndPassword(tempUsername, tempPassword);

			if(userObj == null) {
				//if not present, invalid credentials
				throw new Exception("*Wrong username or password");
			}

			else if(!userObj.getRole().equalsIgnoreCase(role)) {
				//if user is not librarian, then access will be denied
				throw new Exception("*Access denied");
			}
		}
		//if present, user would be successfully logged in 
		return userObj;
	}
}
