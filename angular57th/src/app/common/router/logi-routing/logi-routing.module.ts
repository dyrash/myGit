import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LogiBusinessRoutingModule } from './logi-business-routing/logi-business-routing.module';
import { LogiPurchaseRoutingModule } from './logi-purchase-routing/logi-purchase-routing.module';
import { LogiProductionRoutingModule } from './logi-production-routing/logi-production-routing.module';

const routes: Routes = [

  {path : 'business/*', component : LogiBusinessRoutingModule},
  {path : 'purchase/*', component : LogiPurchaseRoutingModule},
  {path : 'production/*', component : LogiProductionRoutingModule},

]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forChild(routes),LogiBusinessRoutingModule,LogiPurchaseRoutingModule,LogiProductionRoutingModule
  ],
  exports:[RouterModule]
})
export class LogiRoutingModule { }
