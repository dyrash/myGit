import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgGridModule } from 'ag-grid-angular';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DemoMaterialModule } from '../common/shared/material.module';
import { AccSlipModule } from './acc-slip/acc-slip.module';
import { AccStatementModule } from './acc-statement/acc-statement.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,AgGridModule.withComponents([])
    ,FormsModule,NgbModule,DemoMaterialModule,AccSlipModule
    ,AccStatementModule
  ],
  exports:[]
})
export class AccModule { }
