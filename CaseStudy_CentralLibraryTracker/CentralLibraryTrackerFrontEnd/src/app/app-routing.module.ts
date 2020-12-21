import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BooksComponent } from './books/books.component';
import { AddBooksComponent } from './add-books/add-books.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MembersComponent } from './members/members.component';
import { AddMembersComponent } from './add-members/add-members.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { AuthGuard } from './auth.guard';
import { HomeComponent } from './home/home.component';
import { BookIssueComponent } from './book-issue/book-issue.component';
import { IssuedBookListComponent } from './issued-book-list/issued-book-list.component';
import { ReturnBookComponent } from './return-book/return-book.component';
import { ReturnedBookHistoryComponent } from './returned-book-history/returned-book-history.component';
import { PenaltyReportComponent } from './penalty-report/penalty-report.component';
import { MaxBookIssuedReportComponent } from './max-book-issued-report/max-book-issued-report.component';


const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'books', component: BooksComponent, canActivate: [AuthGuard] },
    { path: 'books/add', component: AddBooksComponent, canActivate: [AuthGuard] },
    { path: 'books/edit/:id', component: AddBooksComponent, canActivate: [AuthGuard] },
    { path: 'members', component: MembersComponent, canActivate: [AuthGuard] },
    { path: 'members/add', component: AddMembersComponent, canActivate: [AuthGuard] },
    { path: 'members/edit/:id', component: AddMembersComponent, canActivate: [AuthGuard] },
    { path: 'issue_book', component: BookIssueComponent, canActivate: [AuthGuard] },
    { path: 'issued_book', component: IssuedBookListComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegistrationComponent },
    { path: 'returnBook/:id', component: ReturnBookComponent, canActivate: [AuthGuard] },
    { path: 'returned_book/history', component: ReturnedBookHistoryComponent, canActivate: [AuthGuard] },
    { path: 'penaltyReport/:id', component: PenaltyReportComponent, canActivate: [AuthGuard] },
    { path: 'order-wise/max-issued-books/report', component: MaxBookIssuedReportComponent, canActivate: [AuthGuard] },
    { path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

export const routingComponents = [HomeComponent,
                                  BooksComponent,
                                  AddBooksComponent,
                                  PageNotFoundComponent,
                                  MembersComponent,
                                  AddMembersComponent,
                                  LoginComponent,
                                  RegistrationComponent,
                                  BookIssueComponent,
                                  IssuedBookListComponent,
                                  ReturnBookComponent,
                                  ReturnedBookHistoryComponent,
                                  PenaltyReportComponent,
                                  MaxBookIssuedReportComponent
                                ];
