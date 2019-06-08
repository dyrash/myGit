import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';
import { LogiDeliveryComponent } from 'src/app/logi-views/logi-business/logi-delivery-view/logi-delivery/logi-delivery.component';
import { ContractComponent } from 'src/app/logi-views/logi-business/contract/contract.component';
import { EstimateComponent } from 'src/app/logi-views/logi-business/estimate/estimate.component';
import { RoutingGuard } from '../../routing.guard';
import { MainViewComponent } from 'src/app/sys-views/main-view/main-view.component';

const routes: Routes = [
  {path: 'main', component: MainViewComponent ,
  children:[
 
  {path : 'logi/business/slipForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'logi/business/EstimateForm', component : EstimateComponent,canActivate: [RoutingGuard]},
  {path : 'logi/business/ContractForm', component : ContractComponent,canActivate: [RoutingGuard]},
  {path : 'logi/business/DeliveryForm', component : LogiDeliveryComponent,canActivate: [RoutingGuard]}
  ]}
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forRoot(routes)
  ],
  exports:[RouterModule]
})
export class LogiBusinessRoutingModule { }
