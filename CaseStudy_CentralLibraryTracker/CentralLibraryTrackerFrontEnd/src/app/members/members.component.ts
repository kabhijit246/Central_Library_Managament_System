import { Component, OnInit } from '@angular/core';
import { MembersService } from '../services/members.service';
import { Router } from '@angular/router';
import { Members } from '../models/members';
import { PageEvent } from '@angular/material';

@Component({
  selector: 'app-members',
  templateUrl: './members.component.html',
  styleUrls: ['./members.component.css']
})
export class MembersComponent implements OnInit {

  user = '';
  members = [];
  errorMsg = '';
  public filters = {keyword: '', sortBy: 'Email'};
  pageSize = 10;
  lowValue = 0;
  highValue = 10;

  constructor(private membersService: MembersService,
              private router: Router) { }

  ngOnInit() {
    this.membersList();
    this.user = localStorage.getItem('user');
  }

  // method to send id of member to the MemberService class which is going to be deleted from db
  removeMember(id: number) {
    this.membersService.removeMember(id).subscribe(
      data => { this.membersList(); }
    );
  }

  // method to get array of member details from MemberService class
  membersList() {
    this.membersService.getMembers().subscribe(
      data => this.members = this.membersFilter(data),
      error => this.errorMsg = error
    );
  }

  // filtering members from table on the basis of email, name, major
  membersFilter(member: Members[]) {
      if (this.filters.sortBy === 'Email') {
        return member.filter((m) => {
          return m.email.toLowerCase().includes(this.filters.keyword.toLowerCase());
        });
      }

      else if (this.filters.sortBy === 'Name') {
        return member.filter((m) => {
          return m.name.toLowerCase().includes(this.filters.keyword.toLowerCase());
        });
      }

      else if (this.filters.sortBy === 'Major') {
        return member.filter((m) => {
          return m.major.toLowerCase().includes(this.filters.keyword.toLowerCase());
        });
      }
    }

  public getPaginatorData(event: PageEvent): PageEvent {
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
  }

  logout() {
    this.router.navigateByUrl('/login');
    localStorage.removeItem('user');
  }
}
