import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CodeManageViewComponent } from './code-manage-view/code-manage-view.component';
import { CustomerManageViewComponent } from './customer-manage-view/customer-manage-view.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { AgGridModule } from 'ag-grid-angular';
import { RouterModule } from '@angular/router';
import { DemoMaterialModule } from '../common/shared/material.module';
import { ReduxTestComponent } from './redux-test-view/redux-test/redux-test.component';
import { CutomerViewComponent } from './cutomer-view/cutomer-view.component';

const CORE_COMPONENTS = [ReduxTestComponent,
  CodeManageViewComponent,CustomerManageViewComponent,
  
  CutomerViewComponent]


@NgModule({
  declarations: [CodeManageViewComponent, CustomerManageViewComponent
    
     
    , ReduxTestComponent,ReduxTestComponent, CutomerViewComponent],
  imports: [
    CommonModule,RouterModule ,AgGridModule.withComponents([]),FormsModule,NgbModule,DemoMaterialModule
  ],
  exports: CORE_COMPONENTS
})
export class SysModule { }
