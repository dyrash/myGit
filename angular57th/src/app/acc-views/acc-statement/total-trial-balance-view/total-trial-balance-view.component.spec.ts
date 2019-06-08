import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TotalTrialBalanceViewComponent } from './total-trial-balance-view.component';

describe('TotalTrialBalanceViewComponent', () => {
  let component: TotalTrialBalanceViewComponent;
  let fixture: ComponentFixture<TotalTrialBalanceViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TotalTrialBalanceViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TotalTrialBalanceViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
