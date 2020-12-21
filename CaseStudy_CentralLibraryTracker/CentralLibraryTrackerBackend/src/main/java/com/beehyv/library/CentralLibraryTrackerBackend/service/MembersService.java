package com.beehyv.library.CentralLibraryTrackerBackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.MembersRepository;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Member;

@Service
public class MembersService {
	
	@Autowired
	private MembersRepository membersRepo;

	public void setMembersRepo(MembersRepository membersRepo) {
		this.membersRepo = membersRepo;
	}

	public List<Member> memberList() {
		return membersRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	public Optional<Member> getMemberById(int id) {
		return membersRepo.findById(id);
	}

	public Member addMember(Member m) {
		return membersRepo.save(m);
	}

	public void removeMember(int id) {
		membersRepo.deleteById(id); 
	}

	public Member getMemberByEmail(String email) {
		return membersRepo.findByEmail(email);
	}

}
