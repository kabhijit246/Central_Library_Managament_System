package com.beehyv.library.CentralLibraryTrackerBackend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Member;
import com.beehyv.library.CentralLibraryTrackerBackend.service.MembersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private MembersService membersService;

	public void setMembersService(MembersService membersService) {
		this.membersService = membersService;
	}

	@GetMapping("/list") //method to get the list of members present in db by get mapping
	public List<Member> memberList() {
		return membersService.memberList();
	}

	@GetMapping("/member/{id}") //method to get details of member of corresponding id by get mapping 
	public Optional<Member> getMemberWithId(@PathVariable int id) throws Exception {
		Optional<Member> m = membersService.getMemberById(id);
		if(m.isEmpty()) {
			//if member not found corresponding to any id , it will throw exception
			throw new Exception("*No record found for this Id");
		}
		else {
			//else, will return member details of corresponding id
			return m;
		}
	}

	@PostMapping("/add") //method for adding member in db by post mapping
	public Member addMember(@RequestBody Member m) throws Exception {
		String tempEmail = m.getEmail();
		if(tempEmail != null && !"".equals(tempEmail)) {
			//fetching member by their email from db if present
			Member memberObj = membersService.getMemberByEmail(tempEmail);
			if(memberObj != null) {
				//if present, then it will throw exception
				throw new Exception("*Member with this email "+ tempEmail +" already exists.");
			}
		}
		Member memberObj = null;
		//else, member will be added
		memberObj = membersService.addMember(m);
		return memberObj;
	}

	@DeleteMapping("/remove/{id}") //method to delete member of corresponding id from db by delete mapping
	public String removeMember(@PathVariable int id){
		membersService.removeMember(id);
		return "Member removed with ID: "+ id;
	}

	@PutMapping("/edit") //method to edit/update member details in db by put mapping
	public Member updateMember(@RequestBody Member m) {
		membersService.addMember(m);
		return m;
	}

}
