import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorkinstructionComponent } from './workinstruction/workinstruction.component';
import { MpsViewComponent } from './mps-view/mps-view.component';
import { LogiProductionResultViewComponent } from './logi-production-result-view/logi-production-result-view.component';
import { RouterModule } from '@angular/router';
import { AgGridModule } from 'ag-grid-angular';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DemoMaterialModule } from 'src/app/common/shared/material.module';
import { MrpViewComponent } from './mrp-view/mrp-view.component';
import { MrpGatheringViewComponent } from './mrp-view/mrp-gathering-view/mrp-gathering-view.component';

@NgModule({
  declarations: [LogiProductionResultViewComponent, MpsViewComponent,WorkinstructionComponent, MrpViewComponent, MrpGatheringViewComponent],
  imports: [
    CommonModule,RouterModule,AgGridModule.withComponents([]),FormsModule,NgbModule,DemoMaterialModule
  ],
  exports:[LogiProductionResultViewComponent, MpsViewComponent,WorkinstructionComponent,MrpViewComponent,MrpGatheringViewComponent]
})
export class LogiProductionModule { }
