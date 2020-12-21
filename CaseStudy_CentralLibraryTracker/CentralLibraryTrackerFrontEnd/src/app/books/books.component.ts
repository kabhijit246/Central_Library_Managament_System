  import { Component, OnInit, Input } from '@angular/core';
  import { BooksService } from '../services/books.service';
  import { Books } from '../models/books';
  import { Router } from '@angular/router';
  import { PageEvent } from '@angular/material';

  @Component({
    selector: 'app-books',
    templateUrl: './books.component.html',
    styleUrls: ['./books.component.css']
  })
  export class BooksComponent implements OnInit {

    user = '';
    books = [];
    errorMsg = '';
    public filters = {keyword: '', sortBy: 'Title'};
    pageSize = 10;
    lowValue = 0;
    highValue = 10;

    constructor(private booksService: BooksService,
                private router: Router) { }

    ngOnInit() {
      this.user = localStorage.getItem('user');
      this.booksList();
    }

    // method to send id of book to the BookService class which is going to be deleted from db
    removeBook(id: number) {
      this.booksService.removeBook(id).subscribe(
        data => { this.booksList(); },
        error => this.errorMsg = error
      );
    }

    // method to get array of book details from BookService class
    booksList() {
      this.booksService.getBooks().subscribe(
        data => this.books = this.booksFilter(data),
        error => this.errorMsg = error
      );
    }

    // filtering books from table on the basis of title, subject, author
    booksFilter(book: Books[]) {
      if (this.filters.sortBy === 'Title') {
        return book.filter((b) => {
          return b.title.toLowerCase().includes(this.filters.keyword.toLowerCase());
        });
      }

      else if (this.filters.sortBy === 'Subject') {
        return book.filter((b) => {
          return b.subject.toLowerCase().includes(this.filters.keyword.toLowerCase());
        });
      }

      else if (this.filters.sortBy === 'Author') {
        return book.filter((b) => {
          return b.author.toLowerCase().includes(this.filters.keyword.toLowerCase());
        });
      }
    }

    public getPaginatorData(event: PageEvent): PageEvent {
      this.lowValue = event.pageIndex * event.pageSize;
      this.highValue = this.lowValue + event.pageSize;
      return event;
    }

    logout() {
      this.router.navigateByUrl('/login');
      localStorage.removeItem('user');
    }
}
