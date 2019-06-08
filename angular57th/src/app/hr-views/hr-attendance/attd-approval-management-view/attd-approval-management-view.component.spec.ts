import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttdApprovalManagementViewComponent } from './attd-approval-management-view.component';

describe('AttdApprovalManagementViewComponent', () => {
  let component: AttdApprovalManagementViewComponent;
  let fixture: ComponentFixture<AttdApprovalManagementViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttdApprovalManagementViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttdApprovalManagementViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
