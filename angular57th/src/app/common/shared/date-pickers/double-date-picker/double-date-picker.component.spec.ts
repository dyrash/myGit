import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoubleDatePickerComponent } from './double-date-picker.component';

describe('DoubleDatePickerComponent', () => {
  let component: DoubleDatePickerComponent;
  let fixture: ComponentFixture<DoubleDatePickerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoubleDatePickerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoubleDatePickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
