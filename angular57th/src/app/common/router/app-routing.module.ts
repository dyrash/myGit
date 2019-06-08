import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginFormComponent } from 'src/app/sys-views/main-view/login-form/login-form.component';
import { ReduxTestComponent } from 'src/app/sys-views/redux-test-view/redux-test/redux-test.component';

const routes: Routes = [
  {path : '', redirectTo: 'login-form', pathMatch:'full'},
  {path : 'login-form', component : LoginFormComponent},
  {path : 'redux', component : ReduxTestComponent}
] ;

@NgModule({
  
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
