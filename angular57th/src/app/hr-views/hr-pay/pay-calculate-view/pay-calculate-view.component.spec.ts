import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayCalculateViewComponent } from './pay-calculate-view.component';

describe('PayCalculateViewComponent', () => {
  let component: PayCalculateViewComponent;
  let fixture: ComponentFixture<PayCalculateViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayCalculateViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayCalculateViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
