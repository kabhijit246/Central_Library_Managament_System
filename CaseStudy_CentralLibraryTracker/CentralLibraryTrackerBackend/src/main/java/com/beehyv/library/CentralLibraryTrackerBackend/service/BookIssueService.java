package com.beehyv.library.CentralLibraryTrackerBackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.BookIssueRepository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.BookIssue;

@Service
public class BookIssueService {

	@Autowired
	private BookIssueRepository repo;

	public void setRepo(BookIssueRepository repo) {
		this.repo = repo;
	}
	
	public BookIssue getIssuedBookByBookIdAndMemberId(int bookId, int memberId) {
		return repo.findByBookIdAndMemberId(bookId, memberId);
	}

	public BookIssue add(BookIssue b) {
		return repo.save(b);
	}

	public void removeBook(int id) {
		repo.deleteById(id);
	}

	public List<BookIssue> listIssuedBook() {
		return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public Optional<BookIssue> getBookById(int id) {
		return repo.findById(id);
	}
}
