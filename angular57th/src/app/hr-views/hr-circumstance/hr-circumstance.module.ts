import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HrTestViewComponent } from './hr-test-view/hr-test-view.component';

@NgModule({
  declarations: [HrTestViewComponent],
  imports: [
    CommonModule
  ],
  exports:[HrTestViewComponent]
})
export class HrCircumstanceModule { }
