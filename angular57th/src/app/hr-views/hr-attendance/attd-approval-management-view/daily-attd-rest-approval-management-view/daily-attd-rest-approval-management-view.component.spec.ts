import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DailyAttdRestApprovalManagementViewComponent } from './daily-attd-rest-approval-management-view.component';

describe('DailyAttdRestApprovalManagementViewComponent', () => {
  let component: DailyAttdRestApprovalManagementViewComponent;
  let fixture: ComponentFixture<DailyAttdRestApprovalManagementViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DailyAttdRestApprovalManagementViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DailyAttdRestApprovalManagementViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
