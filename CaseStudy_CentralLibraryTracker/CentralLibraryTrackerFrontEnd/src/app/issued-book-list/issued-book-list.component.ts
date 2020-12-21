import { Component, OnInit } from '@angular/core';
import { BookIssueService } from '../services/book-issue.service';
import { Router } from '@angular/router';
import { BookIssue } from '../models/book-issue';
import { PageEvent } from '@angular/material';

@Component({
  selector: 'app-issued-book-list',
  templateUrl: './issued-book-list.component.html',
  styleUrls: ['./issued-book-list.component.css']
})
export class IssuedBookListComponent implements OnInit {

  user = '';
  issuedBook = [];
  public filters = {keyword: '', sortBy: 'Email'};
  pageSize = 10;
  lowValue = 0;
  highValue = 10;

  constructor(private bookIssueService: BookIssueService,
              private router: Router) { }

  ngOnInit() {
    this.user = localStorage.getItem('user');
    this.issuedList();
  }

  // method to send id of issued-book to the BookIssueService class which is going to be deleted from db
  returnBook(id: number) {
    this.bookIssueService.removeIssuedBook(id).subscribe(
      data => this.issuedList()
    );
  }

  // method to get array of issued-book details from BookIssueService class
  issuedList() {
    this.bookIssueService.getIssuedBook().subscribe(
      data => this.issuedBook = this.filterIssuedBook(data)
    );
  }

  // filtering issued-books from table on the basis of title, subject, author, name of member, email
  filterIssuedBook(book: BookIssue[]) {
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
