import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GridDatePickerComponent } from './grid-date-picker.component';

describe('GridDatePickerComponent', () => {
  let component: GridDatePickerComponent;
  let fixture: ComponentFixture<GridDatePickerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GridDatePickerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GridDatePickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
