import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaxBookIssuedReportComponent } from './max-book-issued-report.component';

describe('MaxBookIssuedReportComponent', () => {
  let component: MaxBookIssuedReportComponent;
  let fixture: ComponentFixture<MaxBookIssuedReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaxBookIssuedReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaxBookIssuedReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
