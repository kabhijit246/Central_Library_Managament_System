package com.beehyv.library.CentralLibraryTrackerBackend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Books;
import com.beehyv.library.CentralLibraryTrackerBackend.service.BooksService;

@CrossOrigin("*")
@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	private BooksService booksService;

	public void setBooksService(BooksService booksServices){
		this.booksService = booksServices;
	}

	@GetMapping("/list") //method to get the list of books present in db by get mapping
	public List<Books> listBooks() {
		return booksService.listBooks();
	}

	@GetMapping("/book/{id}") //method to get details of book of corresponding id by get mapping 
	public Optional<Books> getBookWithId(@PathVariable int id) throws Exception {
		Optional<Books> b = booksService.getBooksById(id);

		if(b.isEmpty()) {
			//if book not found corresponding to any id , it will throw exception
			throw new Exception("*No record found for this Id");
		}
		else {
			//else, will return book details of corresponding id
			return b;
		}
	}

	@PostMapping("/add") //method for adding book in db by post mapping
	public Books addBooks(@RequestBody Books b) throws Exception{
		String tempSubject = b.getSubject();
		String tempTitle = b.getTitle();
		String tempAuthor = b.getAuthor();
		String tempPublisher = b.getPublisher();
		String tempCopyright = b.getCopyright();
		int tempEdition = b.getEdition();
		int tempPages = b.getPages();
		long tempIsbn = b.getIsbn();
		String tempLibraryName = b.getLibraryName();

		//fetching book by their details from db if present
		Books bookObj = booksService.getBooksByAll(tempAuthor, tempCopyright, tempEdition, tempIsbn, tempLibraryName, tempPages, tempPublisher, tempSubject, tempTitle);
		if(bookObj != null) {
			//if present, then it will throw exception
			throw new Exception("*This book is already available in shelf no. "+ bookObj.getShelfNo() +", just update the quantity");
		}
		else {
			//else, book will be added
			booksService.addBooks(b);
		}
		return b;
	}

	@DeleteMapping("/remove/{id}") //method to delete book of corresponding id from db by delete mapping
	public String removeBook(@PathVariable int id) {
		booksService.removeBooks(id);
		return "Book removed with ID: "+ id;
	}

	@PutMapping("/edit") //method to edit/update book details in db by put mapping
	public Books updateBook(@RequestBody Books b) {
		booksService.addBooks(b);
		return b;
	}
}
