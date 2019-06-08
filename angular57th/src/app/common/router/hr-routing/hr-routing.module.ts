import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { HrAttendanceRoutingModule } from './hr-attendance-routing/hr-attendance-routing.module';
import { HrCircumstanceRoutingModule } from './hr-circumstance-routing/hr-circumstance-routing.module';
import { HrAppointmentRoutingModule } from './hr-appointment-routing/hr-appointment-routing.module';
import { HrPmRoutingModule } from './hr-pm-routing/hr-pm-routing.module';


const routes: Routes = [
  {path : 'hr/attendance/*', component : HrAttendanceRoutingModule},
  {path : 'hr/circumstance/*', component : HrCircumstanceRoutingModule},
  {path : 'hr/pm/*', component : HrPmRoutingModule},
  {path : 'hr/appointment/*', component : HrAppointmentRoutingModule}
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forChild(routes),HrAttendanceRoutingModule,HrCircumstanceRoutingModule,HrPmRoutingModule,HrAppointmentRoutingModule
  ],
  exports:[RouterModule]
})
export class HrRoutingModule { }
