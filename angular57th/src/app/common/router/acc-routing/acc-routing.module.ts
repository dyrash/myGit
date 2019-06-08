import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AccStatementRoutingModule } from './acc-statement-routing/acc-statement-routing.module';
import { AccBookMgtRoutingModule } from './acc-book-mgt-routing/acc-book-mgt-routing.module';
import { RoutingGuard } from '../routing.guard';
import { AccSlipRoutingModule } from './acc-slip-routing/acc-slip-routing.module';

const routes: Routes = [
  {path : 'main/acc/statement/*', component : AccStatementRoutingModule,canActivate: [RoutingGuard]},
  {path : 'main/acc/slip/*', component : AccSlipRoutingModule,canActivate: [RoutingGuard]},
  {path : 'main/acc/accBookMgt/*', component : AccBookMgtRoutingModule,canActivate: [RoutingGuard]}
]
@NgModule({
    declarations: [],
    imports: [
      CommonModule,RouterModule.forChild(routes),AccStatementRoutingModule,AccBookMgtRoutingModule,AccSlipRoutingModule
    ],
    exports:[RouterModule]
  })
export class AccRoutingModule { }
