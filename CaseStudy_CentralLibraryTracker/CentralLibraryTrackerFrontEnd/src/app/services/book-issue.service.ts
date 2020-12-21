import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { BookIssue } from '../models/book-issue';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BookIssueService {

  private getUrl1 = 'http://localhost:8080/book_issue/book';
  private getUrl2 = 'http://localhost:8080/book_issue/issued';

  constructor(private http: HttpClient) { }

  // method to send the http post request to save the issued-books in db
  issued(issue: BookIssue): Observable<BookIssue> {
    return this.http.post<BookIssue>(this.getUrl1, issue).pipe(
      map(response => response),
      catchError(this.errorHandler)
    );
  }

  // method to send the http get request to get the issued-books list from backend
  getIssuedBook(): Observable<BookIssue[]> {
    return this.http.get<BookIssue[]>(this.getUrl2).pipe(
      map(response => response),
      catchError(this.errorHandler)
    );
  }

  // method to send the http get request to get a issued-book details of corresponding id
  getBook(id: number): Observable<BookIssue> {
    return this.http.get<BookIssue>(`${this.getUrl1}/${id}`).pipe(
      map( response => response ),
      catchError(this.errorHandler)
    );
  }

  // method to send the http delete request to delete the issued-book from db of corresponding id
  removeIssuedBook(id: number): Observable<any> {
    return this.http.delete(`${this.getUrl1}/${id}`, {responseType: 'text'}).pipe(
      map( response => response ),
      catchError(this.errorHandler)
    );
  }

  // method to get error message from the backend if any
  errorHandler(error: HttpErrorResponse) {
    return throwError(error.error.message || '*server error');
  }
}
