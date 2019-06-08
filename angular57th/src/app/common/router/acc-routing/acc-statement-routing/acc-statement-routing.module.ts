import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';
import { TotalTrialBalanceViewComponent } from 'src/app/acc-views/acc-statement/total-trial-balance-view/total-trial-balance-view.component';
import { MainViewComponent } from 'src/app/sys-views/main-view/main-view.component';

 

const routes: Routes = [
  {path: 'main', component: MainViewComponent ,
  children:[
  {path : 'acc/statement/totalTrialBalanceForm', component : TotalTrialBalanceViewComponent},
  {path : 'acc/statement/preFinancialStatementForm', component : ReduxTestComponent},
  {path : 'acc/statement/incomeStatementForm', component : ReduxTestComponent},
  {path : 'acc/statement/financialstatementForm', component : ReduxTestComponent},
  {path : 'acc/statement/preIncomeStatementForm', component : ReduxTestComponent}
  ]}
]
@NgModule({
    declarations: [],
    imports: [
      CommonModule,RouterModule.forChild(routes)
    ],
    exports:[RouterModule]
  })
export class AccStatementRoutingModule { }
