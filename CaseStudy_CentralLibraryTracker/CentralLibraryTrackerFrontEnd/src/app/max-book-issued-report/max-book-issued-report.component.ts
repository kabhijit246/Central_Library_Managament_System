import { Component, OnInit } from '@angular/core';
import { ReturnBookService } from '../services/return-book.service';

@Component({
  selector: 'app-max-book-issued-report',
  templateUrl: './max-book-issued-report.component.html',
  styleUrls: ['./max-book-issued-report.component.css']
})
export class MaxBookIssuedReportComponent implements OnInit {

  maxIssuedBook = [];
  constructor(private returnBookService: ReturnBookService) { }

  ngOnInit() {
    // getting 10 best borrowed books from returned-books list via ReturnBookService
    this.returnBookService.getCategoryWiseMaxBookIssued().subscribe(
      data => { this.maxIssuedBook = data; }
    );
  }

  // method to print 10 best borrowed books
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
