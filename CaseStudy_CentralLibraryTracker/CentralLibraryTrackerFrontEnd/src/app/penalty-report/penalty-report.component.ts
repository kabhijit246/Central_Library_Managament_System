import { Component, OnInit } from '@angular/core';
import { ReturnBookService } from '../services/return-book.service';
import { ActivatedRoute } from '@angular/router';
import { ReturnBook } from '../models/returnbook';

@Component({
  selector: 'app-penalty-report',
  templateUrl: './penalty-report.component.html',
  styleUrls: ['./penalty-report.component.css']
})
export class PenaltyReportComponent implements OnInit {

  returnBook: ReturnBook = new ReturnBook();
  fine: number;

  constructor(private returnBookService: ReturnBookService,
              private activateRoute: ActivatedRoute) { }

  ngOnInit() {
    const isIdPresent = this.activateRoute.snapshot.paramMap.has('id');
    if (isIdPresent) {
      const id = +this.activateRoute.snapshot.paramMap.get('id');
      // getting returned-book details of corresponding id from ReturnBookService class
      this.returnBookService.getBook(id).subscribe(
        data => {this.returnBook = data;
                 // Calculation of fine on the basis of number of days of late return 
                 if (this.returnBook.status === 0) {
                      this.fine = 0;
                  }
                  else if (this.returnBook.status === 1) {
                      this.fine = 5;
                  }
                  else if (this.returnBook.status > 1 && this.returnBook.status < 6) {
                      this.fine = 10 * (this.returnBook.status);
                  }
                  else {
                      this.fine = 15 * (this.returnBook.status);
                  }
                }
      );
    }
  }

  // method to print penalty report
  print(id: any) {
    const printContents = document.getElementById(id).innerHTML;
    const originalContents = document.body.innerHTML;
    document.body.innerHTML = printContents;
    window.focus();
    window.print();
    window.close();
    document.body.innerHTML = originalContents;
    window.location.reload();
  }
}
