import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';
import { MainViewComponent } from 'src/app/sys-views/main-view/main-view.component';

 
 

const routes: Routes = [
  {path: 'main', component: MainViewComponent ,
  children:[
  {path : 'hr/appointment/PersonalAppointmentForm', component : ReduxTestComponent},
  {path : 'hr/appointment/AppointmentForm', component : ReduxTestComponent}
  ]}
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class HrAppointmentRoutingModule { }
