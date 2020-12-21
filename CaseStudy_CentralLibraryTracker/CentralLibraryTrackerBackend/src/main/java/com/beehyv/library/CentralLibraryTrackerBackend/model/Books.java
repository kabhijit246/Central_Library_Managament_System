package com.beehyv.library.CentralLibraryTrackerBackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Books {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
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
	@Column
	private int copies;
	@Column(name="library_name")
	private String libraryName;
	@Column(name="shelf_no")
	private int shelfNo;

	//constructor using fields
	public Books(String subject, String title, String author, String publisher, String copyright, int edition,
			int pages, long isbn, int copies, String libraryName, int shelfNo) {
		this.subject = subject;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.copyright = copyright;
		this.edition = edition;
		this.pages = pages;
		this.isbn = isbn;
		this.copies = copies;
		this.libraryName = libraryName;
		this.shelfNo = shelfNo;
	}
	//default constructor
	public Books() {

	}

	//following are the getters and setters of all the fields
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}

	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public int getShelfNo() {
		return shelfNo;
	}
	public void setShelfNo(int shelfNo) {
		this.shelfNo = shelfNo;
	}
}
