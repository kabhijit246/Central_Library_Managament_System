package com.beehyv.library.CentralLibraryTrackerBackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.ReturnBookRepository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.ReturnBook;

@Service
public class ReturnBookService {

	@Autowired
	private ReturnBookRepository returnBookRepo;

	public void setReturnBookRepo(ReturnBookRepository returnBookRepo) {
		this.returnBookRepo = returnBookRepo;
	}

	public List<ReturnBook> listReturnedBook() {
		return returnBookRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public ReturnBook add(ReturnBook b) {
		return returnBookRepo.save(b);
	}

	public Optional<ReturnBook> getBookById(int id) {
		return returnBookRepo.findById(id);
	}

	public List<ReturnBook> findBySubject() {
		return returnBookRepo.findMaximumUsedBooks();
	}

}
