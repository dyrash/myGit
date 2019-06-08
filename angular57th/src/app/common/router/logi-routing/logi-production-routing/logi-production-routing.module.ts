import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LogiProductionResultViewComponent } from 'src/app/logi-views/logi-production/logi-production-result-view/logi-production-result-view.component';
import { MpsViewComponent } from 'src/app/logi-views/logi-production/mps-view/mps-view.component';
import { WorkinstructionComponent } from 'src/app/logi-views/logi-production/workinstruction/workinstruction.component';
import { RoutingGuard } from '../../routing.guard';
import { MrpViewComponent } from 'src/app/logi-views/logi-production/mrp-view/mrp-view.component';
import { MainViewComponent } from 'src/app/sys-views/main-view/main-view.component';





const routes: Routes = [
  {path: 'main', component: MainViewComponent ,
  children:[
  {path : 'logi/production/WorkinstructionForm', component : WorkinstructionComponent,canActivate: [RoutingGuard]},
  {path : 'logi/production/ProductionHandlingForm', component : LogiProductionResultViewComponent,canActivate: [RoutingGuard]},
  {path : 'logi/production/MPSForm', component : MpsViewComponent,canActivate: [RoutingGuard]},
  {path : 'logi/production/MRPForm', component : MrpViewComponent,canActivate: [RoutingGuard]}
  ]}
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class LogiProductionRoutingModule { }
