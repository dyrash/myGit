import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TotalTrialBalanceViewComponent } from './total-trial-balance-view/total-trial-balance-view.component';
import { AgGridModule } from 'ag-grid-angular';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DemoMaterialModule } from 'src/app/common/shared/material.module';

@NgModule({
  declarations: [TotalTrialBalanceViewComponent ],
  imports: [
    CommonModule,AgGridModule.withComponents([]),FormsModule,NgbModule,DemoMaterialModule
  ],
  exports:[TotalTrialBalanceViewComponent]
})
export class AccStatementModule { }
