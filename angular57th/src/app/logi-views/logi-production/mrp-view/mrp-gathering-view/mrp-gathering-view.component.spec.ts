import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MrpGatheringViewComponent } from './mrp-gathering-view.component';

describe('MrpGatheringViewComponent', () => {
  let component: MrpGatheringViewComponent;
  let fixture: ComponentFixture<MrpGatheringViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MrpGatheringViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MrpGatheringViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
