import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerManageViewComponent } from './customer-manage-view.component';

describe('CustomerManageViewComponent', () => {
  let component: CustomerManageViewComponent;
  let fixture: ComponentFixture<CustomerManageViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerManageViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerManageViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
