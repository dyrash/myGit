import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeManageViewComponent } from './code-manage-view.component';

describe('CodeManageViewComponent', () => {
  let component: CodeManageViewComponent;
  let fixture: ComponentFixture<CodeManageViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CodeManageViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CodeManageViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
