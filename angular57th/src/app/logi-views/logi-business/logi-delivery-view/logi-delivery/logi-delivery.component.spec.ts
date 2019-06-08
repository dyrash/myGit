import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogiDeliveryComponent } from './logi-delivery.component';

describe('LogiDeliveryComponent', () => {
  let component: LogiDeliveryComponent;
  let fixture: ComponentFixture<LogiDeliveryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogiDeliveryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogiDeliveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
