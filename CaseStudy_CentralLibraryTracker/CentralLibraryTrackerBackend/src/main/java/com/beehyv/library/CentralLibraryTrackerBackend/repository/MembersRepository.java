package com.beehyv.library.CentralLibraryTrackerBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Member;

@Repository
public interface MembersRepository extends JpaRepository<Member, Integer> {

	//custom method to search by email in db
	Member findByEmail(String email);

}
