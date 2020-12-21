package com.beehyv.library.CentralLibraryTrackerBackend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.beehyv.library.CentralLibraryTrackerBackend.model.BookIssue;
import com.beehyv.library.CentralLibraryTrackerBackend.service.BookIssueService;

@CrossOrigin("*")
@RestController
@RequestMapping("/book_issue")
public class BookIssueController {

	@Autowired
	private BookIssueService bookIssueService;

	public void setBookIssueService(BookIssueService bookIssueService) {
		this.bookIssueService = bookIssueService;
	}

	@PostMapping("/book") //this method is for adding book and member details to issued book list by post mapping
	public BookIssue issued(@RequestBody BookIssue b) throws Exception {
		int tempBookId = b.getBookId();
		int tempMemberId = b.getMemberId();
		if(tempBookId != ' ' && tempMemberId != ' ') {
			//checking whether book and member details are present in db or not
			BookIssue obj = bookIssueService.getIssuedBookByBookIdAndMemberId(tempBookId, tempMemberId);
			if(obj != null) {
				//if present, throwing exception
				throw new Exception("*This member already issued this book and not returned yet");
			}
		}
		BookIssue obj = null;
		//else, adding all details to db
		obj = bookIssueService.add(b);
		return obj;
	}

	@DeleteMapping("/book/{id}") // method used for deleting details of corresponding id by delete mapping
	public String deleteBookIssued(@PathVariable int id) {
		bookIssueService.removeBook(id);
		return "Book is returned";
	}

	@GetMapping("/issued") // method to get the list of all the details present in db by get mapping
	public List<BookIssue> listIssuedBook() {
		List<BookIssue> list = bookIssueService.listIssuedBook();
		return list;
	}

	@GetMapping("/book/{id}") //method to get a details of corresponding id if present from db by get mapping
	public Optional<BookIssue> bookById(@PathVariable int id) {
		return bookIssueService.getBookById(id);
	}
}
