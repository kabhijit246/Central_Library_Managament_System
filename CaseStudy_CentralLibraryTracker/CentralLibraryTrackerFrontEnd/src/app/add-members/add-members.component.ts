import { Component, OnInit } from '@angular/core';
import { Members } from '../models/members';
import { MembersService } from '../services/members.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-members',
  templateUrl: './add-members.component.html',
  styleUrls: ['./add-members.component.css']
})
export class AddMembersComponent implements OnInit {

  member: Members = new Members();
  errorMsg = '';

  constructor(private membersService: MembersService,
              private router: Router,
              private activateRoute: ActivatedRoute) { }

  ngOnInit() {
    const isIdPresent = this.activateRoute.snapshot.paramMap.has('id');
    if (isIdPresent) {
      const id = +this.activateRoute.snapshot.paramMap.get('id');
      // fetching member details by sending a id of member to the backend
      this.membersService.getMember(id).subscribe(
        data => this.member = data,
        error => this.errorMsg = error
      );
    }
  }

  // method to send the inputed details of member to the backend to save in db
  addMembers() {
    this.membersService.addmembers(this.member).subscribe(
      data => { this.router.navigateByUrl('/members'); },
      error => this.errorMsg = error
    );
  }

  // method to send the id of member which is going to be deleted
  removeMember(id: number) {
    this.membersService.removeMember(id).subscribe(
      data => { this.router.navigateByUrl('/members'); },
      error => this.errorMsg = error
    );
  }

  // method to send the updated/edited details of member to the backend to save in db
  updateMember() {
    this.membersService.updateMember(this.member).subscribe(
      data => { this.router.navigateByUrl('/members'); },
      error => this.errorMsg = error
    );
  }
}
