package com.beehyv.library.CentralLibraryTrackerBackend;

import org.junit.jupiter.api.TestMethodOrder;
import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;
import com.beehyv.library.CentralLibraryTrackerBackend.model.BookIssue;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.BookIssueRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class BookIssueCrudTests {
	
	@Autowired
	private BookIssueRepository bookIssueRepository;
	
	@Test
	@Order(1)
	@Rollback(false)
	@SuppressWarnings("deprecation") //test method for adding details of issued books to the list 
	public void testAddIssuedBook() {
		BookIssue book = new BookIssue(1, 1, "CS", "React", "Hope", 3, 415, 5678, "LLB", "Abhijit", "abhijit@gmail.com", "CS", Date.valueOf("2020-11-03"), Date.valueOf("2020-11-15"));
		BookIssue savedIssuedBook = bookIssueRepository.save(book);
		Assert.notNull(savedIssuedBook);
	}
	
	@Test
	@Order(2) //test method for getting issued book details of corresponding id
	public void testGetIssuedBookById() {
		int id = 1;
		Optional<BookIssue> book = bookIssueRepository.findById(id);
		assertThat(book.isEmpty()).isEqualTo(false);
	}
	
	@Test
	@Order(3) //test method for getting the list of all issued book from db
	public void testListIssuedBooks() {
		List<BookIssue> book = bookIssueRepository.findAll();
		assertThat(book).size().isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(false) //test method for deleting issued book from the db of corresponding id
	public void testDeleteIssuedBook() {
		int id = 1;
		Optional<BookIssue> beforeDelete = bookIssueRepository.findById(id);
		bookIssueRepository.deleteById(id);
		Optional<BookIssue> afterDelete = bookIssueRepository.findById(id);
		assertThat(beforeDelete.isEmpty()).isEqualTo(false);
		assertThat(afterDelete.isEmpty()).isNotEqualTo(false);
	}

}
