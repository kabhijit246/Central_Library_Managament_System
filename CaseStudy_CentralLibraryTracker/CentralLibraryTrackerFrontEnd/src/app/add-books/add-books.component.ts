import { Component, OnInit } from '@angular/core';
import { BooksService } from '../services/books.service';
import { Books } from '../models/books';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-books',
  templateUrl: './add-books.component.html',
  styleUrls: ['./add-books.component.css']
})
export class AddBooksComponent implements OnInit {

  book: Books = new Books();
  errorMsg = '';
  constructor(private booksService: BooksService,
              private router: Router,
              private activateRoute: ActivatedRoute) { }

  ngOnInit() {
    const isIdPresent = this.activateRoute.snapshot.paramMap.has('id');
    if (isIdPresent) {
      const id = +this.activateRoute.snapshot.paramMap.get('id');
      // fetching book details by sending a id of book to the backend
      this.booksService.getBook(id).subscribe(
        data => this.book = data,
        error => this.errorMsg = error
      );
    }
  }

  // method to send the inputed details of book to the backend to save in db
  addBooks() {
    this.booksService.addbooks(this.book).subscribe(
      data => { this.router.navigateByUrl('/books'); },
      error => this.errorMsg = error
    );
  }

  // method to send the id of book which is going to be deleted
  removeBook(id: number) {
    this.booksService.removeBook(id).subscribe(
      data => { this.router.navigateByUrl('/books'); },
      error => this.errorMsg = error
    );
  }

  // method to send the updated/edited details of book to the backend to save in db
  updateBook() {
    this.booksService.updateBook(this.book).subscribe(
      data => { this.router.navigateByUrl('/books'); },
      error => this.errorMsg = error
    );
  }

}
