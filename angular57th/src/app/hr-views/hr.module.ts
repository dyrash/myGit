import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HrAttendanceModule } from './hr-attendance/hr-attendance.module';
import { AgGridModule } from 'ag-grid-angular';
import { FormsModule } from '@angular/forms';
import { NgbModule, NgbTimepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { DemoMaterialModule } from '../common/shared/material.module';
import { MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import { RouterModule } from '@angular/router';
import { HrPayModule } from './hr-pay/hr-pay.module';
import { HrAppointmentModule } from './hr-appointment/hr-appointment.module';
import { HrCircumstanceModule } from './hr-circumstance/hr-circumstance.module';
import { HrPmModule } from './hr-pm/hr-pm.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,HrAttendanceModule,MatDatepickerModule,AgGridModule.withComponents([]),FormsModule,NgbModule,DemoMaterialModule,MatNativeDateModule
    ,RouterModule,NgbTimepickerModule
    ,HrPayModule,HrAppointmentModule,HrAttendanceModule,HrCircumstanceModule,HrPmModule ],
    exports:[
      HrAttendanceModule,HrPayModule,HrAppointmentModule,HrCircumstanceModule,HrPmModule
     
      ,AgGridModule,FormsModule,NgbModule,DemoMaterialModule,MatNativeDateModule
      ,RouterModule,NgbTimepickerModule
  ]
})
export class HrModule { }
