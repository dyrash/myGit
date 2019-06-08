import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SlipRegisterComponent } from './slip-register/slip-register.component';
import { AgGridModule } from 'ag-grid-angular';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DemoMaterialModule } from 'src/app/common/shared/material.module';

@NgModule({
  declarations: [SlipRegisterComponent],
  imports: [
    CommonModule,AgGridModule.withComponents([]),FormsModule,NgbModule,DemoMaterialModule
  ],
  exports:[SlipRegisterComponent]
})
export class AccSlipModule { }
