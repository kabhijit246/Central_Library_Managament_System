package com.beehyv.library.CentralLibraryTrackerBackend;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;
import com.beehyv.library.CentralLibraryTrackerBackend.model.ReturnBook;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.ReturnBookRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class ReturnBookCrudTests {
	
	@Autowired
	private ReturnBookRepository returnBookRepository;
	
	@Test
	@Order(1)
	@Rollback(false)
	@SuppressWarnings("deprecation") //test method for adding details of returned books to the list
	public void testAddReturnedBook() {
		ReturnBook book = new ReturnBook(1, 1, "CS", "React", "Hope", 3, 415, 5678, "LLB", "Abhijit", "abhijit@gmail.com", "CS", Date.valueOf("2020-11-03"), Date.valueOf("2020-11-15"), Date.valueOf("2020-11-14"), 0);
		ReturnBook savedBook = returnBookRepository.save(book);
		Assert.notNull(savedBook);
	}
	
	@Test
	@Order(2) //test method for getting returned book details of corresponding id
	public void testGetReturnedBookById() {
		int id = 1;
		Optional<ReturnBook> book = returnBookRepository.findById(id);
		assertThat(book.isEmpty()).isEqualTo(false);
	}
	
	@Test
	@Order(3) //test method for getting the list of all returned books from db
	public void testListReturnedBooks() {
		List<ReturnBook> book = returnBookRepository.findAll();
		assertThat(book).size().isGreaterThan(0);
	}

}
