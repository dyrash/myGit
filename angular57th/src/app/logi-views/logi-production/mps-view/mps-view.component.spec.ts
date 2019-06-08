import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MpsViewComponent } from './mps-view.component';

describe('MpsViewComponent', () => {
  let component: MpsViewComponent;
  let fixture: ComponentFixture<MpsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MpsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MpsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
