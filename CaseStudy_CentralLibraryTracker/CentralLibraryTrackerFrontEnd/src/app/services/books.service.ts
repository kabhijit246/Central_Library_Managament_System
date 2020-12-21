import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { Books } from '../models/books';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  private getUrl1 = 'http://localhost:8080/books/list';
  private getUrl2 = 'http://localhost:8080/books/add';
  private getUrl3 = 'http://localhost:8080/books/book';
  private getUrl4 = 'http://localhost:8080/books/remove';
  private getUrl5 = 'http://localhost:8080/books/edit';

  constructor(private http: HttpClient) { }

  // method to send the http get request to get the books list from backend
  getBooks(): Observable<Books[]> {
     return this.http.get<Books[]>(this.getUrl1).pipe(
       map( response => response ),
       catchError(this.errorHandler)
     );
  }

  // method to send the http post request to save the books in db
  addbooks(books: Books): Observable<Books> {
    return this.http.post<Books>(this.getUrl2, books).pipe(
      map( response => response ),
      catchError(this.errorHandler)
    );
  }

  // method to send the http get request to get a book details of corresponding id
  getBook(id: number): Observable<Books> {
    return this.http.get<Books>(`${this.getUrl3}/${id}`).pipe(
      map( response => response ),
      catchError(this.errorHandler)
    );
  }

  // method to send the http delete request to delete the book from db of corresponding id
  removeBook(id: number): Observable<any> {
    return this.http.delete(`${this.getUrl4}/${id}`, {responseType: 'text'}).pipe(
      map( response => response ),
      catchError(this.errorHandler)
    );
  }

  // method to send http put request to update/edit book details
  updateBook(book: Books): Observable<Books> {
    return this.http.put<Books>(this.getUrl5, book).pipe(
      map( response => response ),
      catchError(this.errorHandler)
    );
  }

  // method to get error message from the backend if any
  errorHandler(error: HttpErrorResponse) {
    return throwError(error.error.message || '*server error');
  }

}
