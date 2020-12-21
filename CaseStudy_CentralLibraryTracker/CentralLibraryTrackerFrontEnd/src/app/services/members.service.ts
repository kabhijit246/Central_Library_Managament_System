import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { Members } from '../models/members';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MembersService {

  private getUrl1 = 'http://localhost:8080/members/list';
  private getUrl2 = 'http://localhost:8080/members/add';
  private getUrl3 = 'http://localhost:8080/members/member';
  private getUrl4 = 'http://localhost:8080/members/remove';
  private getUrl5 = 'http://localhost:8080/members/edit';

  constructor(private http: HttpClient) { }

  // method to send the http get request to get the members list from backend
  getMembers(): Observable<Members[]> {
     return this.http.get<Members[]>(this.getUrl1).pipe(
       map( response => response ),
       catchError(this.errorHandler)
     );
  }

  // method to send the http post request to save the members in db
  addmembers(members: Members): Observable<Members> {
    return this.http.post<Members>(this.getUrl2, members).pipe(
      map( response => response ),
      catchError(this.errorHandler)
    );
  }

  // method to send the http get request to get a member details of corresponding id
  getMember(id: number): Observable<Members> {
    return this.http.get<Members>(`${this.getUrl3}/${id}`).pipe(
      map( response => response ),
      catchError(this.errorHandler)
    );
  }

  // method to send the http delete request to delete the member from db of corresponding id
  removeMember(id: number): Observable<any> {
    return this.http.delete(`${this.getUrl4}/${id}`, {responseType: 'text'}).pipe(
      map( response => response),
      catchError(this.errorHandler)
    );
  }

  // method to send http put request to update/edit member details
  updateMember(member: Members): Observable<Members> {
    return this.http.put<Members>(this.getUrl5, member).pipe(
      map( response => response),
      catchError(this.errorHandler)
    );
  }

  // method to get error message from the backend if any
  errorHandler(error: HttpErrorResponse) {
    return throwError(error.error.message || '*server error');
  }
}
