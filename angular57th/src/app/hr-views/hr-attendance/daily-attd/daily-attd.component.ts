import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { AgGridNg2 } from 'ag-grid-angular';
import { HttpClient } from '@angular/common/http';
import { Store } from 'redux';
import { AttendanceService } from '../attendance.service';
import { dataState } from 'src/app/common/redux/core/code.state';
import { MatDatepicker } from '@angular/material';
import { dataStore } from 'src/app/common/redux/core/code.store';

@Component({
  selector: 'app-daily-attd',
  templateUrl: './daily-attd.component.html',
  styleUrls: ['./daily-attd.component.css']
})
export class DailyAttdComponent implements OnInit {
  @ViewChild('agGrid') agGrid: AgGridNg2;
  @ViewChild(MatDatepicker) datepicker: MatDatepicker<Date>;
  
  attendanceService:AttendanceService;
  private dataArray = [];
  model;
  model2;
  fromDate;
  toDate;
  counter: any[];

  constructor(public http:HttpClient, @Inject(dataStore) private store: Store<dataState>) {
    this.attendanceService=new AttendanceService(http);
    store.subscribe(() => this.readState());
   }

  ngOnInit() {
    this.Http();
  }

  Http(){
    this.attendanceService.transaction("hr/attendance/findDailyAttdList.do","","empCode=1234 fromDate="+this.fromDate+" toDate="+this.toDate+"").subscribe((result : any[])=>{
      this.dataArray = result;
      console.log(this.dataArray);
    });
    this.readState(); 
  }
  
  readState() {
   // const state: dataState = this.store.getState() as dataState;
   // this.codeArray=state.code;
   // console.log(this.codeArray);
  }
  dailyAttdcolumnDefs = [
    {headerName: '사원코드', field: 'empCode',checkboxSelection: true},
    {headerName: '기준일', field: 'basicDay'},
    {headerName: '근태구분', field: 'attdTypeCode', editable:true},
    {headerName: '시간', field: 'time'},
    {headerName: '금액', field: 'cost'},
    {headerName: '신청사유', field: 'cause'},
    {headerName: '승인상태', field: 'approvalStatus'}
  ];

  getSelectedAttd(){
    this.findStatment(this.model,this.model2);
    this.Http();
  }

  leadingZeros(date, num) {
      var zero = '';
      date = date.toString();
     
      if (date.length < num) {
       for (var i = 0; i < num - date.length; i++)
        zero += '0';
      }
      return zero + date;
   }


  findStatment(model:string,model2:string){
    
   this.fromDate=this.createDate(model);
   this.toDate=this.createDate(model2);

   //alert(this.toDate);
  }

  calDate;
  year;
  month;
  date;

  createDate(d:string){
    this.calDate=new Date(d);
    this.year=this.calDate.getFullYear().toString();
    this.month=this.leadingZeros((parseInt(this.calDate.getMonth())+1).toString(),2);
    this.date=this.leadingZeros(this.calDate.getDate().toString(),2);
       
   return this.year+this.month+this.date;
  }
}

