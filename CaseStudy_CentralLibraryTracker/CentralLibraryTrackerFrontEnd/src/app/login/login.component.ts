import { Component, OnInit } from '@angular/core';
import { Users } from '../models/users';
import { UsersService } from '../services/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: Users = new Users();
  errorMsg = '';
  constructor(private usersService: UsersService,
              private router: Router) { }

  ngOnInit() {
  }

  // method sending user credential to the UserService class
  loginUser() {
    this.usersService.loginUser(this.user).subscribe(
      data => { localStorage.setItem('user', this.user.username);
                this.router.navigateByUrl('/home'); },
      error => this.errorMsg = error
    );
  }

}
