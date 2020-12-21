import { Component, OnInit } from '@angular/core';
import { Users } from '../models/users';
import { UsersService } from '../services/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public user: Users = new Users();
  errorMsg = '';

  constructor(private usersService: UsersService,
              private router: Router) { }

  ngOnInit() {
  }

  // method sending user details from inputed fields to UserService class
  registerUser() {
    return this.usersService.registerUser(this.user).subscribe(
      data => { this.router.navigateByUrl('/login'); },
      error => this.errorMsg = error
    );
  }
}
