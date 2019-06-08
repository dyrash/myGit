import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';
import { RoutingGuard } from '../../routing.guard';
import { MainViewComponent } from 'src/app/sys-views/main-view/main-view.component';

const routes: Routes = [
  {path: 'main', component: MainViewComponent ,
  children:[
  {path : 'logi/purchase/BOMForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'logi/purchase/WarehousingForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'logi/purchase/GiveOrderForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'logi/purchase/ItemForm', component : ReduxTestComponent,canActivate: [RoutingGuard]},
  {path : 'logi/purchase/InventoryForm', component : ReduxTestComponent,canActivate: [RoutingGuard]}
  ]}
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forChild(routes)
  ],
  exports:[RouterModule]
})
export class LogiPurchaseRoutingModule { }
