package com.beehyv.library.CentralLibraryTrackerBackend;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;
import com.beehyv.library.CentralLibraryTrackerBackend.model.Member;
import com.beehyv.library.CentralLibraryTrackerBackend.repository.MembersRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class MembersCrudTests {
	
	@Autowired
	private MembersRepository membersRepository;

	
	@Test
	@Order(1)
	@Rollback(false)
	@SuppressWarnings("deprecation") //test method for adding details of members to the list
	public void testAddMember() {
		Member member = new Member("Abhijit123@", "Abhijit", "abhijit@gmail.com", "CS");
		Member savedMember = membersRepository.save(member);
		Assert.notNull(savedMember);
	}
	
	@Test
	@Order(2) //test method for getting member details of corresponding id
	public void testGetMemberById() {
		int id = 1;
		Optional<Member> member = membersRepository.findById(id);
		assertThat(member.isEmpty()).isEqualTo(false);
	}
	
	@Test
	@Order(4)
	@Rollback(false) //test method for updating/editing member details which is present in db
	public void testUpdateMember() {
		int id = 1;
		Member member = new Member("Abhijit1@", "Abhi", "abhijit@gmail.com", "Chemical");
		member.setId(id);
		membersRepository.save(member);
		Optional<Member> updatedMember = membersRepository.findById(id);
		assertThat(updatedMember.isEmpty()).isEqualTo(false);
	}
	
	@Test
	@Order(3) //test method for getting the list of all members from db
	public void testListMembers() {
		List<Member> member = membersRepository.findAll();
		assertThat(member).size().isGreaterThan(0);
	}
	
	@Test
	@Order(5)
	@Rollback(false) //test method for deleting member from the db of corresponding id
	public void testDeleteMember() {
		int id = 1;
		Optional<Member> beforeDelete = membersRepository.findById(id);
		membersRepository.deleteById(id);
		Optional<Member> afterDelete = membersRepository.findById(id);
		assertThat(beforeDelete.isEmpty()).isEqualTo(false);
		assertThat(afterDelete.isEmpty()).isNotEqualTo(false);
	}

}
