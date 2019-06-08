import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkinstructionComponent } from './workinstruction.component';

describe('WorkinstructionComponent', () => {
  let component: WorkinstructionComponent;
  let fixture: ComponentFixture<WorkinstructionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkinstructionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkinstructionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
