import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MainViewComponent } from '../../sys-views/main-view/main-view.component';
import { SysRoutingModule } from './sys-routing/sys-routing.module';
import { HrRoutingModule } from './hr-routing/hr-routing.module';
import { LogiRoutingModule } from './logi-routing/logi-routing.module';
import { AccRoutingModule } from './acc-routing/acc-routing.module';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';
import { RoutingGuard } from './routing.guard';
import { WelcomeComponent } from 'src/app/sys-views/main-view/welcome/welcome.component';

const mainRoutes: Routes = [
  { path: 'main', component: MainViewComponent,children:[
    {path : 'welcome', component : WelcomeComponent}  
  ]} ,

  {path : 'main/sys/*', component : SysRoutingModule},
  {path : 'main/hr/*', component : HrRoutingModule },
  {path : 'main/logi/*', component : LogiRoutingModule},
  {path : 'main/acc/*', component : AccRoutingModule}
   
  
]


@NgModule({
  imports: [RouterModule.forChild(mainRoutes),SysRoutingModule,HrRoutingModule,LogiRoutingModule,AccRoutingModule],
  exports: [RouterModule],
})
export class MainViewRoutingModule { }
