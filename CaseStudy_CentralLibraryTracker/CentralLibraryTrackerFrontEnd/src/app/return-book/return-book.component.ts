import { Component, OnInit } from '@angular/core';
import { ReturnBookService } from '../services/return-book.service';
import { ReturnBook } from '../models/returnbook';
import { ActivatedRoute, Router } from '@angular/router';
import { BookIssueService } from '../services/book-issue.service';
import { BookIssue } from '../models/book-issue';
import { BooksService } from '../services/books.service';
import { Books } from '../models/books';

@Component({
  selector: 'app-return-book',
  templateUrl: './return-book.component.html',
  styleUrls: ['./return-book.component.css']
})
export class ReturnBookComponent implements OnInit {

  returnBook: ReturnBook = new ReturnBook();
  issuedBook: BookIssue = new BookIssue();
  book: Books = new Books();
  books: Books = new Books();

  constructor(private returnBookService: ReturnBookService,
              private bookIssueService: BookIssueService,
              private bookService: BooksService,
              private router: Router,
              private activateRoute: ActivatedRoute) { }

  ngOnInit() {
    const isIdPresent = this.activateRoute.snapshot.paramMap.has('id');
    if (isIdPresent) {
      const id = +this.activateRoute.snapshot.paramMap.get('id');
      // getting issued-book details from BookIssueService class
      this.bookIssueService.getBook(id).subscribe(
        data => {this.issuedBook = data;
                 this.returnBook.bookId = this.issuedBook.bookId;
                 this.returnBook.memberId = this.issuedBook.memberId;
                 this.returnBook.subject = this.issuedBook.subject;
                 this.returnBook.title = this.issuedBook.title;
                 this.returnBook.author = this.issuedBook.author;
                 this.returnBook.edition = this.issuedBook.edition;
                 this.returnBook.pages = this.issuedBook.pages;
                 this.returnBook.isbn = this.issuedBook.isbn;
                 this.returnBook.libraryName = this.issuedBook.libraryName;
                 this.returnBook.name = this.issuedBook.name;
                 this.returnBook.email = this.issuedBook.email;
                 this.returnBook.major = this.issuedBook.major;
                 this.returnBook.issueDate = this.issuedBook.issueDate;
                 this.returnBook.expireDate = this.issuedBook.expireDate;
                }
      );
    }
  }

  returnedBook() {
    const date1 = new Date(this.returnBook.returnDate);
    const date2 = new Date(this.returnBook.expireDate);
    const id = +this.activateRoute.snapshot.paramMap.get('id');

    // comparing return date and expiry date of issued-book
    if (date1.getTime() <= date2.getTime()) {
      // number of days late is 0 if returned date is within expiry date
      this.returnBook.status = 0;
    }
    else {
      // calculation of number of days late if returned date exceeding expiry date
      const late = (((date1.getTime() - date2.getTime()) / 86400000));
      this.returnBook.status = late;
    }

    // sending returned-book details to the ReturnBookService class
    this.returnBookService.returned(this.returnBook).subscribe(
      data => { // getting book details by id of returned-book from BookService class
                  this.bookService.getBook(this.issuedBook.bookId).subscribe(
                    response => { this.book = response;
                                  // updating stock of corresponding book which is returned to library
                                  this.book.copies++;
                                  this.bookService.updateBook(this.book).subscribe();
                                  window.alert('Put that book in shelf no. ' + this.book.shelfNo);
                                }
                  );
              }
    );

    // deleting issued-book which is successfully returned to library
    this.bookIssueService.removeIssuedBook(id).subscribe(
      data => { this.router.navigateByUrl('/returned_book/history'); }
    );

  }

}
