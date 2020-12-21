import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { ReturnBook } from '../models/returnbook';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReturnBookService {

  private getUrl1 = 'http://localhost:8080/return_book/book';
  private getUrl2 = 'http://localhost:8080/return_book/returned';
  private getUrl3 = 'http://localhost:8080/return_book/category-wise/max-book-issued';

  constructor(private http: HttpClient) { }

  // method to send the http post request to save the returned-books in db
  returned(returnBook: ReturnBook): Observable<ReturnBook> {
    return this.http.post<ReturnBook>(this.getUrl1, returnBook).pipe(
      map(response => response),
      catchError(this.errorHandler)
    );
  }

  // method to send the http get request to get the returned-books list from backend
  getBook(id: number): Observable<ReturnBook> {
    return this.http.get<ReturnBook>(`${this.getUrl1}/${id}`).pipe(
      map( response => response ),
      catchError(this.errorHandler)
    );
  }

  // method to send the http get request to get a issued-book details of corresponding id
  getReturnedBook(): Observable<ReturnBook[]> {
    return this.http.get<ReturnBook[]>(this.getUrl2).pipe(
      map(response => response),
      catchError(this.errorHandler)
    );
  }

  // method to send the http get request to get list of top 10 best borrowed books
  getCategoryWiseMaxBookIssued(): Observable<ReturnBook[]> {
    return this.http.get<ReturnBook[]>(this.getUrl3).pipe(
      map(response => response),
      catchError(this.errorHandler)
    );
  }

  // method to get error message from the backend if any
  errorHandler(error: HttpErrorResponse) {
    return throwError(error.error.message || '*server error');
  }

}
