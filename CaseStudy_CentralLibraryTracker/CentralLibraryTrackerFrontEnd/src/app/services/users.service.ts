import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Users } from '../models/users';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private getUrl1 = 'http://localhost:8080/users/login';
  private getUrl2 = 'http://localhost:8080/users/register';

  constructor(private http: HttpClient) { }

  // method to send credentials to backend for validation by http post request
  loginUser(user: Users): Observable<Users> {
    return this.http.post<Users>(this.getUrl1, user)
                    .pipe(catchError(this.errorHandler));
  }

  // method to save new user details in db by sending their details to backend by http post request
  registerUser(user: Users): Observable<Users> {
    return this.http.post<Users>(this.getUrl2, user)
               .pipe(catchError(this.errorHandler));
  }

  // method to return boolean value whether user is logged in or not
  loggedIn() {
    return !!localStorage.getItem('user');
  }

  // method to get error message from backend if any
  errorHandler(error: HttpErrorResponse) {
    return throwError(error.error.message || '*server error');
  }

}
