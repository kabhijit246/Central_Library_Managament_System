package com.beehyv.library.CentralLibraryTrackerBackend.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_issue")
public class BookIssue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name="book_id")
	private int bookId;
	@Column(name="member_id")
	private int memberId;
	@Column
	private String subject;
	@Column
	private String title;
	@Column
	private String author;
	@Column
	private String publisher;
	@Column
	private String copyright;
	@Column
	private int edition;
	@Column
	private int pages;
	@Column
	private long isbn;
	@Column(name="library_name")
	private String libraryName;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String major;
	@Column(name="issue_date")
	private Date issueDate;
	@Column(name="expire_date")
	private Date expireDate;

	//constructor using fields
	public BookIssue(int bookId, int memberId, String subject, String title, String author, int edition, int pages,
			long isbn, String libraryName, String name, String email, String major, Date issueDate, Date expireDate) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.subject = subject;
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.pages = pages;
		this.isbn = isbn;
		this.libraryName = libraryName;
		this.name = name;
		this.email = email;
		this.major = major;
		this.issueDate = issueDate;
		this.expireDate = expireDate;
	}
	//default constructor
	public BookIssue() {

	}

	//following are the getters and setters of all the fields
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}

	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

}
