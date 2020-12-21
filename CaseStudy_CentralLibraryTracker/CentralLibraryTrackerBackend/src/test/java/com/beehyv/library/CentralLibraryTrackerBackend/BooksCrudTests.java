package com.beehyv.library.CentralLibraryTrackerBackend;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Books;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.BooksRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class BooksCrudTests {
	
	@Autowired
	private BooksRepository booksRepository;

	
	@Test
    @Order(1)
	@Rollback(false)
	@SuppressWarnings("deprecation") //test method for adding details of books to the list 
	public void testAddBook() {
		Books book = new Books("CSE", "React", "Hope", "Roli", "Author", 3, 415, 5678, 4, "LLB", 22);
		Books savedBook = booksRepository.save(book);
		Assert.notNull(savedBook);
	}
	
	@Test
	@Order(2) //test method for getting book details of corresponding id
	public void testGetBookById() {
		int id = 1;
		Optional<Books> book = booksRepository.findById(id);
		assertThat(book.isEmpty()).isEqualTo(false);
	}
	
	@Test
	@Order(4)
	@Rollback(false) //test method for updating/editing book details which is present in db
	public void testUpdateBook() {
		int id = 1;
		Books book = new Books("CS", "Angular", "David", "Arihant", "Author", 4, 520, 2456, 4, "LLB", 23);
		book.setId(id);
		booksRepository.save(book);
		Optional<Books> updatedBook = booksRepository.findById(id);
		assertThat(updatedBook.isEmpty()).isEqualTo(false);
	}
	
	@Test
	@Order(3) //test method for getting the list of all books from db
	public void testListBooks() {
		List<Books> book = booksRepository.findAll();
		assertThat(book).size().isGreaterThan(0);
	}
	
	@Test
	@Order(5)
	@Rollback(false) //test method for deleting book from the db of corresponding id
	public void testDeleteBook() {
		int id = 1;
		Optional<Books> beforeDelete = booksRepository.findById(id);
		booksRepository.deleteById(id);
		Optional<Books> afterDelete = booksRepository.findById(id);
		assertThat(beforeDelete.isEmpty()).isEqualTo(false);
		assertThat(afterDelete.isEmpty()).isNotEqualTo(false);
	}
}
