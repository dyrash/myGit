import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AgGridModule } from 'ag-grid-angular';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './common/router/app-routing.module';
import { SysModule } from './sys-views/sys.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { appStoreProviders } from './common/redux/core/app.store';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { RequestInterceptor } from './common/svc/request-interceptor.service';
import { datStoreProviders } from './common/redux/core/code.store';
import { LogiModule } from './logi-views/logi.module';
import { AccModule } from './acc-views/acc.module';
import { HrModule } from './hr-views/hr.module';
import { RoutingGuard } from './common/router/routing.guard';
import { MainViewModule } from './sys-views/main-view/main-view.module';
import { MainViewRoutingModule } from './common/router/main-view-routing.module';
import { NgMaterialMultilevelMenuModule } from 'ng-material-multilevel-menu';


@NgModule({
  declarations: [
    AppComponent
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MainViewRoutingModule,
    NgMaterialMultilevelMenuModule,

    SysModule,
    AgGridModule.withComponents([]),
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,

    LogiModule,
    AccModule,HrModule,MainViewModule

  ],
  providers: [
    appStoreProviders,
    datStoreProviders,
    HttpClientModule,
    RoutingGuard,

    {provide: HTTP_INTERCEPTORS
    ,useClass: RequestInterceptor
    ,multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
