import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';
import { AttdApprovalManagementViewComponent } from 'src/app/hr-views/hr-attendance/attd-approval-management-view/attd-approval-management-view.component';
import { DailyAttdComponent } from 'src/app/hr-views/hr-attendance/daily-attd/daily-attd.component';
import { RoutingGuard } from '../../routing.guard';
import { MainViewComponent } from 'src/app/sys-views/main-view/main-view.component';

const routes: Routes = [
  {path: 'main', component: MainViewComponent ,
  children:[
  {path : 'hr/attendance/OverNightApplicationForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'hr/attendance/MonthlyAttdanceManagementForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'hr/attendance/DailyAttendanceApplicationForm', component : DailyAttdComponent,canActivate: [RoutingGuard]},
  {path : 'hr/attendance/DailyAttdRestApplicationForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'hr/attendance/DailyAttdManagementForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'hr/attendance/attdApprovalManagementForm', component : AttdApprovalManagementViewComponent,canActivate: [RoutingGuard]},
  {path : 'hr/attendance/AnnualApplicationForm', component : ReduxTestComponent,canActivate: [RoutingGuard]}
  ]}
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class HrAttendanceRoutingModule { }
