package com.beehyv.library.CentralLibraryTrackerBackend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Users;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class UsersTests {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	@Order(1)
	@Rollback(false)
	@SuppressWarnings("deprecation") //test method for adding details of users to the list
	public void testAddUser() {
		Users user = new Users("abhijit", "Abhijit123@", "abhijit@gmail.com", "Librarian");
		Users savedUser = userRepository.save(user);
		Assert.notNull(savedUser);
	}
	
	@Test
	@Order(1)
	@SuppressWarnings("deprecation") //test method for checking valid credentials
	public void testLoginUser() {
		String username = "abhijit";
		String password = "Abhijit123@";
		Users user = userRepository.findByUsernameAndPassword(username, password);
		Assert.notNull(user);
	}
}
