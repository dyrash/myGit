import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';
import { SlipRegisterComponent } from 'src/app/acc-views/acc-slip/slip-register/slip-register.component';
import { MainViewComponent } from 'src/app/sys-views/main-view/main-view.component';

const routes: Routes = [
  {path: 'main', component: MainViewComponent ,
  children:[
  {path : 'acc/slip/slipRegisterForm', component : SlipRegisterComponent},
  {path : 'acc/slip/slipApproveForm', component : ReduxTestComponent}
  ]}
]
@NgModule({
    declarations: [],
    imports: [
      CommonModule,RouterModule.forChild(routes)
    ],
    exports:[RouterModule]
  })
export class AccSlipRoutingModule { }
