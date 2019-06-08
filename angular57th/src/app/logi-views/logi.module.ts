import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AgGridModule } from 'ag-grid-angular';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DemoMaterialModule } from '../common/shared/material.module';
import { LogiBusinessModule } from './logi-business/logi-business.module';
import { LogiProductionModule } from './logi-production/logi-production.module';
import { LogiPurchaseModule } from './logi-purchase/logi-purchase.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule,AgGridModule.withComponents([])
    ,FormsModule,NgbModule,DemoMaterialModule
    ,LogiBusinessModule,LogiProductionModule
    ,LogiPurchaseModule
  ],
  exports:[LogiBusinessModule,LogiProductionModule,LogiPurchaseModule]
})
export class LogiModule { }
