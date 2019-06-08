import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TimeViewComponent } from './time-view/time-view.component';
import { MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import { AgGridModule } from 'ag-grid-angular';
import { FormsModule } from '@angular/forms';
import { NgbModule, NgbTimepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { DemoMaterialModule } from 'src/app/common/shared/material.module';
import { AttdApprovalManagementViewComponent } from './attd-approval-management-view/attd-approval-management-view.component';
import { DailyAttdApprovalManagementViewComponent } from './attd-approval-management-view/daily-attd-approval-management-view/daily-attd-approval-management-view.component';
import { DailyAttdRestApprovalManagementViewComponent } from './attd-approval-management-view/daily-attd-rest-approval-management-view/daily-attd-rest-approval-management-view.component';
import { DayAnnualAttdApprovalManagementViewComponent } from './attd-approval-management-view/day-annual-attd-approval-management-view/day-annual-attd-approval-management-view.component';
import { OverNightAttdApprovalManagementViewComponent } from './attd-approval-management-view/over-night-attd-approval-management-view/over-night-attd-approval-management-view.component';
import { RouterModule } from '@angular/router';
import { DailyAttdComponent } from './daily-attd/daily-attd.component';

@NgModule({
  declarations: [
    AttdApprovalManagementViewComponent,DailyAttdApprovalManagementViewComponent
    ,DailyAttdRestApprovalManagementViewComponent
    ,DayAnnualAttdApprovalManagementViewComponent
    ,OverNightAttdApprovalManagementViewComponent
   ,TimeViewComponent
   ,DailyAttdComponent
    
   ],
  imports: [
    CommonModule,MatDatepickerModule,AgGridModule.withComponents([]),FormsModule,NgbModule,DemoMaterialModule,MatNativeDateModule
    ,RouterModule,NgbTimepickerModule
  ],
  exports:[AttdApprovalManagementViewComponent,TimeViewComponent,
    DailyAttdApprovalManagementViewComponent,DailyAttdRestApprovalManagementViewComponent
    ,DayAnnualAttdApprovalManagementViewComponent,OverNightAttdApprovalManagementViewComponent
    ,DailyAttdComponent
  ]
})
export class HrAttendanceModule { }



