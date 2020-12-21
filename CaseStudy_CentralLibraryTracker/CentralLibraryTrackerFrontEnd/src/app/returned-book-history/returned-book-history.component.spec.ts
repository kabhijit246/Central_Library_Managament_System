import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnedBookHistoryComponent } from './returned-book-history.component';

describe('ReturnedBookHistoryComponent', () => {
  let component: ReturnedBookHistoryComponent;
  let fixture: ComponentFixture<ReturnedBookHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReturnedBookHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReturnedBookHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
