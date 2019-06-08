import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DailyAttdComponent } from './daily-attd.component';

describe('DailyAttdComponent', () => {
  let component: DailyAttdComponent;
  let fixture: ComponentFixture<DailyAttdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DailyAttdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DailyAttdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
