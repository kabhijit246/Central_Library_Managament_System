package com.beehyv.library.CentralLibraryTrackerBackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.BooksRepository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Books;

@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepo;

	public void setBooksRepo(BooksRepository booksRepo) {
		this.booksRepo = booksRepo;
	}

	public List<Books> listBooks() {
		return booksRepo.findAll(Sort.by(Sort.Direction.ASC, "subject"));
	}

	public Optional<Books> getBooksById(int id) {
		return booksRepo.findById(id);
	}

	public Books addBooks(Books b) {
		return booksRepo.save(b);
	}

	public void removeBooks(int id) {
		booksRepo.deleteById(id);
	}

	public Books getBooksByAll(String tempAuthor, String tempCopyright, int tempEdition, long tempIsbn,
			                   String tempLibraryName, int tempPages, String tempPublisher, String tempSubject,
			                   String tempTitle) {
		return booksRepo.findByAuthorAndCopyrightAndEditionAndIsbnAndLibraryNameAndPagesAndPublisherAndSubjectAndTitle
				        (tempAuthor, tempCopyright, tempEdition, tempIsbn, tempLibraryName, tempPages, tempPublisher,
				         tempSubject, tempTitle);
	}
	
}
