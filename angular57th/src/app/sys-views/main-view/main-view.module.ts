import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { MenuTreeComponent } from './menu-tree/menu-tree.component';
import { FooterComponent } from './footer/footer.component';
import { TopComponent } from './top/top.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { MainViewComponent } from './main-view.component';
import { NgMaterialMultilevelMenuModule } from 'ng-material-multilevel-menu';


import { AgGridModule } from 'ag-grid-angular';

import { MenuPipePipe } from 'src/app/common/shared/menu-pipe.pipe';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { WelcomeComponent } from './welcome/welcome.component';



const CORE_COMPONENTS = [MenuPipePipe,MenuTreeComponent,FooterComponent,TopComponent,LoginFormComponent,MainViewComponent,WelcomeComponent]

@NgModule({
  declarations: CORE_COMPONENTS,
  imports: [CommonModule
    ,RouterModule,AgGridModule.withComponents([])
    ,FormsModule,ReactiveFormsModule,NgbModule,NgMaterialMultilevelMenuModule,BrowserAnimationsModule

  ],
  exports : [CORE_COMPONENTS,FormsModule,ReactiveFormsModule]
})
export class MainViewModule { }
