import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OverNightAttdApprovalManagementViewComponent } from './over-night-attd-approval-management-view.component';

describe('OverNightAttdApprovalManagementViewComponent', () => {
  let component: OverNightAttdApprovalManagementViewComponent;
  let fixture: ComponentFixture<OverNightAttdApprovalManagementViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OverNightAttdApprovalManagementViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OverNightAttdApprovalManagementViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
