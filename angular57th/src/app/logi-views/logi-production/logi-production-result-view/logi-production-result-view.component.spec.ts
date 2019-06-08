import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogiProductionResultViewComponent } from './logi-production-result-view.component';

describe('LogiProductionResultViewComponent', () => {
  let component: LogiProductionResultViewComponent;
  let fixture: ComponentFixture<LogiProductionResultViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogiProductionResultViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogiProductionResultViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
