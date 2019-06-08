import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CutomerViewComponent } from 'src/app/sys-views/cutomer-view/cutomer-view.component';
import { CodeManageViewComponent } from 'src/app/sys-views/code-manage-view/code-manage-view.component';
import { CustomerManageViewComponent } from 'src/app/sys-views/customer-manage-view/customer-manage-view.component';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';
import { RoutingGuard } from '../routing.guard';
import { MainViewComponent } from 'src/app/sys-views/main-view/main-view.component';


const routes: Routes = [
  {path: 'main', component: MainViewComponent ,
  children:[
  {path : 'sys/reduxtest', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'sys/materialtest', component : CustomerManageViewComponent,canActivate: [RoutingGuard]},
  {path : 'sys/codeManage', component : CodeManageViewComponent,canActivate: [RoutingGuard]},
  {path : 'sys/customer', component : CutomerViewComponent,canActivate: [RoutingGuard]}
  ]}
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class SysRoutingModule { }
