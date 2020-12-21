package com.beehyv.library.CentralLibraryTrackerBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

	//custom method to search by few book details in db
	Books findByAuthorAndCopyrightAndEditionAndIsbnAndLibraryNameAndPagesAndPublisherAndSubjectAndTitle(String tempAuthor, String tempCopyright, int tempEdition, long tempIsbn, String tempLibraryName,
			int tempPages, String tempPublisher, String tempSubject, String tempTitle);

}
