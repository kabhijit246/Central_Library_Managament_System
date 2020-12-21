package com.beehyv.library.CentralLibraryTrackerBackend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.beehyv.library.CentralLibraryTrackerBackend.model.ReturnBook;
import com.beehyv.library.CentralLibraryTrackerBackend.service.ReturnBookService;

@CrossOrigin("*")
@RestController
@RequestMapping("/return_book")
public class ReturnBookController {

	@Autowired
	private ReturnBookService returnBookService;

	public void setReturnBookService(ReturnBookService returnBookService) {
		this.returnBookService = returnBookService;
	}

	@PostMapping("/book") //method for adding book and member details in db after member returned his/her book by post mapping
	public ReturnBook returned(@RequestBody ReturnBook b) {
		ReturnBook obj = returnBookService.add(b);
		return obj;
	}

	@GetMapping("/returned") //method to get list of books which was returned by member from db by get mapping
	public List<ReturnBook> listIssuedBook() {
		List<ReturnBook> list = returnBookService.listReturnedBook();
		return list;
	}

	@GetMapping("/book/{id}") //method to get details of book and member of corresponding id from db by get mapping
	public Optional<ReturnBook> bookById(@PathVariable int id) {
		return returnBookService.getBookById(id);
	}

	@GetMapping("/category-wise/max-book-issued") //method to get list of top 10 best borrowed books from db by get mapping
	public List<ReturnBook> getSubject() {
		return returnBookService.findBySubject();
	}

}
