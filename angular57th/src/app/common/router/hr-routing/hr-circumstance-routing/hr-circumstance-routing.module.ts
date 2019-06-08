import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';
import { RoutingGuard } from '../../routing.guard';
import { PayCalculateViewComponent } from 'src/app/hr-views/hr-pay/pay-calculate-view/pay-calculate-view.component';
import { HrTestViewComponent } from 'src/app/hr-views/hr-circumstance/hr-test-view/hr-test-view.component';
import { MainViewComponent } from 'src/app/sys-views/main-view/main-view.component';
 

const routes: Routes = [
  {path: 'main', component: MainViewComponent ,
  children:[
  {path : 'hr/circumstance/salMgnForm', component : PayCalculateViewComponent,canActivate: [RoutingGuard]},
  {path : 'hr/circumstance/payStepManagementForm', component : HrTestViewComponent,canActivate: [RoutingGuard]},//호봉
  {path : 'hr/circumstance/PayDeductionForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'hr/circumstance/holidayManagementForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'hr/circumstance/deductionTaxForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'hr/circumstance/annualLeaveManagementForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'hr/circumstance/basicWorktimeManagementForm', component : ReduxTestComponent,canActivate: [RoutingGuard]}
  ]}
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class HrCircumstanceRoutingModule { }
