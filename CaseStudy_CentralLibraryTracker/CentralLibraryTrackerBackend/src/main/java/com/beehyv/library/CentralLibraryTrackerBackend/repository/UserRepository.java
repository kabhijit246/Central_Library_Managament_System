package com.beehyv.library.CentralLibraryTrackerBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	//custom method to search by email in db
	public Users findByEmail(String email);
	//custom method to search by username in db
	public Users findByUsername(String username);
	//custom method to search by username and password in db
	public Users findByUsernameAndPassword(String username, String password);

}
