import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DayAnnualAttdApprovalManagementViewComponent } from './day-annual-attd-approval-management-view.component';

describe('DayAnnualAttdApprovalManagementViewComponent', () => {
  let component: DayAnnualAttdApprovalManagementViewComponent;
  let fixture: ComponentFixture<DayAnnualAttdApprovalManagementViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DayAnnualAttdApprovalManagementViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DayAnnualAttdApprovalManagementViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
