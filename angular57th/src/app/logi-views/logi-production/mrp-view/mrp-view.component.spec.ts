import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MrpViewComponent } from './mrp-view.component';

describe('MrpViewComponent', () => {
  let component: MrpViewComponent;
  let fixture: ComponentFixture<MrpViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MrpViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MrpViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
