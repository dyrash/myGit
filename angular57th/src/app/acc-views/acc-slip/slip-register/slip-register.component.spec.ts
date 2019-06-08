import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlipRegisterComponent } from './slip-register.component';

describe('SlipRegisterComponent', () => {
  let component: SlipRegisterComponent;
  let fixture: ComponentFixture<SlipRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlipRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlipRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
