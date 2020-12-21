import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user = '';

  constructor(private router: Router) { }

  ngOnInit() {
    this.user = localStorage.getItem('user');
  }

  logout() {
    this.router.navigateByUrl('/login');
    localStorage.removeItem('user');
  }

}
