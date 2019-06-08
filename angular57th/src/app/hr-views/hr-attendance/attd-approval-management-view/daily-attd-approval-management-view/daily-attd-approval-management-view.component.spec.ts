import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DailyAttdApprovalManagementViewComponent } from './daily-attd-approval-management-view.component';

describe('DailyAttdApprovalManagementViewComponent', () => {
  let component: DailyAttdApprovalManagementViewComponent;
  let fixture: ComponentFixture<DailyAttdApprovalManagementViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DailyAttdApprovalManagementViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DailyAttdApprovalManagementViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
