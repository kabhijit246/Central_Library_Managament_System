import { Component, OnInit } from '@angular/core';
import { ReturnBook } from '../models/returnbook';
import { Router } from '@angular/router';
import { ReturnBookService } from '../services/return-book.service';
import { PageEvent } from '@angular/material';

@Component({
  selector: 'app-returned-book-history',
  templateUrl: './returned-book-history.component.html',
  styleUrls: ['./returned-book-history.component.css']
})
export class ReturnedBookHistoryComponent implements OnInit {

  user = '';
  returnedBook = [];
  public filters = {keyword: '', sortBy: 'Email'};
  pageSize = 10;
  lowValue = 0;
  highValue = 10;

  constructor(private returnBookService: ReturnBookService,
              private router: Router) {}

  ngOnInit() {
    this.user = localStorage.getItem('user');
    this.returnedList();
  }

  // method to get array of returned-book details from ReturnBookService class
  returnedList() {
    this.returnBookService.getReturnedBook().subscribe(
      data => {this.returnedBook = this.filterReturnedBook(data);
              }
    );
  }

  // filtering returned-books from table on the basis of title, subject, author, name of member, email
  filterReturnedBook(book: ReturnBook[]) {
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

    else if (this.filters.sortBy === 'Name') {
      return book.filter((b) => {
        return b.name.toLowerCase().includes(this.filters.keyword.toLowerCase());
      });
    }

    else if (this.filters.sortBy === 'Email') {
      return book.filter((b) => {
        return b.email.toLowerCase().includes(this.filters.keyword.toLowerCase());
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
