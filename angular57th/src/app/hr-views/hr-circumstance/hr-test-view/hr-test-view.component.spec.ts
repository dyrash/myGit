import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HrTestViewComponent } from './hr-test-view.component';

describe('HrTestViewComponent', () => {
  let component: HrTestViewComponent;
  let fixture: ComponentFixture<HrTestViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HrTestViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HrTestViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
