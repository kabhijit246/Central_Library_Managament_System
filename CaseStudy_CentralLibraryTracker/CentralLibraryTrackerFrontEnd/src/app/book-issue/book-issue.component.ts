import { Component, OnInit } from '@angular/core';
import { Books } from '../models/books';
import { Members } from '../models/members';
import { BooksService } from '../services/books.service';
import { MembersService } from '../services/members.service';
import { BookIssue } from '../models/book-issue';
import { BookIssueService } from '../services/book-issue.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-issue',
  templateUrl: './book-issue.component.html',
  styleUrls: ['./book-issue.component.css']
})
export class BookIssueComponent implements OnInit {

  book: Books = new Books();
  member: Members = new Members();
  issuedBook: BookIssue = new BookIssue();
  errorMsg1 = '';
  errorMsg2 = '';
  errorMsg3 = '';

  constructor(private booksService: BooksService,
              private membersService: MembersService,
              private bookIssueService: BookIssueService,
              private router: Router) {
  }

  ngOnInit() {
  }

  issueBook() {
    // sending book and member details of issued-book to BookIssueService class
    this.bookIssueService.issued(this.issuedBook).subscribe(
      data => { // updating book stock after issuing of book
                this.book.copies--;
                this.booksService.updateBook(this.book).subscribe(
                        resp => { this.router.navigateByUrl('/issued_book'); },
                        error => this.errorMsg1 = error
                );
              },
      error => this.errorMsg1 = error
    );
  }

  searchBookId() {
    // getting book details after searching by id in db
    this.booksService.getBook(this.issuedBook.bookId).subscribe(
      data => { this.book = data;
                // checking whether stock is available or not
                if (this.book.copies === 0) {
                    this.errorMsg2 = '*Book is not available';
                }

                else {
                    this.issuedBook.subject = this.book.subject;
                    this.issuedBook.title = this.book.title;
                    this.issuedBook.author = this.book.author;
                    this.issuedBook.publisher = this.book.publisher;
                    this.issuedBook.copyright = this.book.copyright;
                    this.issuedBook.edition = this.book.edition;
                    this.issuedBook.pages = this.book.pages;
                    this.issuedBook.isbn = this.book.isbn;
                    this.issuedBook.libraryName = this.book.libraryName;
                }
              },
      error => this.errorMsg2 = error
    );
  }

  searchMemberId() {
    // getting member details after searching by id in db
    this.membersService.getMember(this.issuedBook.memberId).subscribe(
      data => {this.member = data;
               this.issuedBook.name = this.member.name;
               this.issuedBook.email = this.member.email;
               this.issuedBook.major = this.member.major;
              },
      error => this.errorMsg3 = error
    );
  }
}

