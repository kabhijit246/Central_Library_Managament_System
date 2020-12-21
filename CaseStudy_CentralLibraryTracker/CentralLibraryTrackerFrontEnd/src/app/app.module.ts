import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { BooksService } from './services/books.service';
import { MembersService } from './services/members.service';
import { UsersService } from './services/users.service';
import { AuthGuard } from './auth.guard';
import { BookIssueService } from './services/book-issue.service';
import { ReturnBookService } from './services/return-book.service';
import { MatPaginatorModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MatPaginatorModule,
    BrowserAnimationsModule,
    MatIconModule
  ],
  providers: [BooksService,
              MembersService,
              UsersService,
              BookIssueService,
              ReturnBookService,
              AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
