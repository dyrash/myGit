import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { DatePipe, Time } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { NgbTimepickerConfig, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';
import { AgGridNg2 } from 'ag-grid-angular';
import { SysService } from 'src/app/common/svc/sys.service';
import { dataStore } from 'src/app/common/redux/core/code.store';
import { Store } from 'redux';
import { dataState } from 'src/app/common/redux/core/code.state';
import { AttendanceService } from '../attendance.service';


@Component({
  selector: 'app-time-view',
  templateUrl: './time-view.component.html',
  styleUrls: ['./time-view.component.css']
})
export class TimeViewComponent implements OnInit {
@ViewChild('agGrid') agGrid: AgGridNg2;

time : NgbTimeStruct ={hour: 12, minute: 30, second: 30};
private url="http://localhost:8282/57thERPangular/";
private codeArray = [];
private sudangArray = [];
attendanceService:AttendanceService;
cause : string = "";
document:Document;

constructor(public http : HttpClient, config: NgbTimepickerConfig,private sysService:SysService,@Inject(dataStore) private store: Store<dataState>) {

  this.sysService=new SysService(http);
  this.attendanceService=new AttendanceService(http);
  store.subscribe(() => this.readState());
  this.readState();

  config.seconds = true;
  config.spinners = false;
 }

 //public dataArray2 = [];
readState() {
  const state: dataState = this.store.getState() as dataState;
 // this.codeArray=this.store.getState().code["codeDetailList"];
  //alert(JSON.stringify(this.codeArray));
  //console.log(this.codeArray);
}

ngOnInit() {
    this.setTime();
    this. findSudangList();
  }

findSudangList(){
    this.http.get(this.url + "hr/circumstance/findSudangList.do").subscribe((result : any[])=>{
      this.sudangArray = result;
      console.log(this.sudangArray);
    });
    this.http.get(this.url + "sys/findCodeDetailList.do").subscribe((result : any[])=>{
      this.codeArray = result;
      console.log(this.codeArray);
    });  
  }

detailCodeName : string ="";
detailCode : string;
setText(detailCodeName:string,detailCode:string){
    //alert(detailCodeName);
    this.detailCodeName=detailCodeName;
    this.detailCode=detailCode;
  }

etcSalName : string ="";
etcSalPrice : string;
setSundang(etcSalName:string,etcSalPrice:string){
    //alert(etcSalName);
    this.etcSalName=etcSalName;
    this.etcSalPrice=etcSalPrice;
  }

  setTime(){
    var datePipe = new DatePipe("en-US");
    var date2 = datePipe.transform(new Date(), 'HH:mm:ss'); // 현재 시간 가져와서 date포맷
    var result= date2.toString();
    var split =result.split(':');
    var HH=parseInt(split[0]);
    var mm=parseInt(split[1]);
    var ss=parseInt(split[2]);
    this.time = {hour: HH , minute: mm, second :ss};
    return this.time;
  }

  newData;
  gridApi;
  array=[];
  hour;
  minute;
  addDailyAttd(time:NgbTimeStruct){
    this.hour=(time.hour).toString();
      if(this.hour.length==1){
        this.hour=0+this.hour;
      }
    alert(this.hour);
    this.minute=(time.minute).toString();
      if(this.minute.length==1){
        this.minute=0+this.minute;
      }
    var addTime=this.hour+this.minute;
    //alert(this.addTime);
    var basicD=this.basicDate();
 
    this.newData={
                           dayAttdSeq: "aaa"
                          , empCode: "1234"
                          , empName: "조아람"
                          , basicDay: basicD
                          , attdTypeCode: this.detailCode
                          , time: addTime
                          , status: "inserted"
                          , cost: this.etcSalPrice
                          , cause: this.cause
                          , approvalStatus : "Y"
    };

    this.array.push(this.newData);
    alert(JSON.stringify(this.array));

    this.attendanceService.transaction("hr/attendance/batchDailyAttd.do",JSON.stringify(this.array),"").subscribe();

    this.detailCodeName="";
    this.etcSalName="";
    this.cause="";
  }

  year;
  month;
  day;
  basicDate(){
    var today=new Date();
    this.year=today.getFullYear();
    this.month=today.getMonth()+1;
    this.day=today.getDate();

    this.year=(this.year).toString();
    this.month=(this.month).toString();
    if(this.month.length==1){
      this.month=0+this.month
    }
    this.day=this.day.toString();
    if(this.day.length==1){
      this.day=0+this.day
    }
    return this.year+this.month+this.day
  }

alertDiv(){
  document.getElementById("alert").style.display="block";
}

}

