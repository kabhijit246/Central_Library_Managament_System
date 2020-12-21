package com.beehyv.library.CentralLibraryTrackerBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.UserRepository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Users;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public void setRepo(UserRepository repo) {
		this.repo = repo;
	}

	public Users addUsers(Users u) {
		return repo.save(u);
	}
	
	public Users getUserByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Users getUserByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	public Users getUserByUsernameAndPassword(String username, String password) {
		return repo.findByUsernameAndPassword(username, password);
	}
	
}
