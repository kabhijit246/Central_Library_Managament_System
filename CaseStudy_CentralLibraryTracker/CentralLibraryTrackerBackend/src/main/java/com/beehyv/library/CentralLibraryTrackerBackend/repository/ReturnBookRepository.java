package com.beehyv.library.CentralLibraryTrackerBackend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.ReturnBook;

@Repository
public interface ReturnBookRepository extends JpaRepository<ReturnBook, Integer>{
	
	@Query(value = "select b.*, count(b.subject) as number from returned_book b " +
	        "group by b.subject " +
	        "order by number desc " +
	        "limit 10", nativeQuery = true)
	List<ReturnBook> findMaximumUsedBooks();

}
