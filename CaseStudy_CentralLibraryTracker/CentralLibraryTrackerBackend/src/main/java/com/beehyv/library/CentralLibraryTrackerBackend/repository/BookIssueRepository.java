package com.beehyv.library.CentralLibraryTrackerBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.BookIssue;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Integer>{

	//custom method to search by book id and member id in db
	public BookIssue findByBookIdAndMemberId(int bookId, int memberId);

}
