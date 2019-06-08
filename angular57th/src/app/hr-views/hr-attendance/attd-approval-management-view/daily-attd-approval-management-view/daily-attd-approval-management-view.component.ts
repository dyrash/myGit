import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AgGridNg2 } from 'ag-grid-angular';
import { HrService } from 'src/app/common/svc/hr.service';


@Component({
  selector: 'app-daily-attd-approval-management-view',
  templateUrl: './daily-attd-approval-management-view.component.html',
  styleUrls: ['./daily-attd-approval-management-view.component.css']
})
export class DailyAttdApprovalManagementViewComponent implements OnInit {
  private http:HttpClient;  
  @ViewChild('statmentGrid') agGrid: AgGridNg2;
  hrService : HrService;

selectDate;
basicDate;
deptCode;
approvalStatus;

//조회버튼을 누르면
  findStatment(basicDate:string){
    console.log(basicDate);
   this.selectDate=this.createDate(basicDate);
 
   //alert(this.selectDate);
   //alert(this.deptCode);
   //alert(this.approvalStatus);

   this.hrService.transaction("attendance/findDailyAttdListByCondition.do","","approvalStatus="+this.approvalStatus+" basicDay="+this.selectDate+" deptCode="+this.deptCode).subscribe((result : any[])=>{
    this.statmentArray = result;
    console.log(this.statmentArray);
  });
 
  }


//선택
getSelectedRows() {
  // const selectedNodes = this.agGrid.api.getSelectedNodes();
  // const selectedData = selectedNodes.map( node => node.data );
  // const selectedDataStringPresentation = selectedData.map( node => node.make + ' ' + node.model).join(', ');
  // alert(`Selected nodes: ${selectedDataStringPresentation}`);
  const selectedNodes = this.agGrid.api.getSelectedNodes();
  //alert(selectedNodes);
  const selectedData = selectedNodes.map( node => node.data);
  alert("data : "+JSON.stringify(selectedData));

}



  //선택승인변경
  checkApproval(){
 
    this.agGrid.api.getSelectedNodes().forEach(element => {
         //alert(element.rowIndex);
         this.statmentArray["dailyAttdList"][element.rowIndex].status="updated"

        if(status!="nomal") {
          if(this.statmentArray["dailyAttdList"][element.rowIndex].approvalStatus=="N"){
         this.statmentArray["dailyAttdList"][element.rowIndex].approvalStatus="Y"
          }else{
            this.statmentArray["dailyAttdList"][element.rowIndex].approvalStatus="N"
          }
        }
        //alert(this.statmentArray["dailyAttdList"][element.rowIndex].status);
        //alert(this.statmentArray["dailyAttdList"][element.rowIndex].approvalStatus);
        //alert(this.statmentArray["dailyAttdList"][element.rowIndex].cost);
        this.agGrid.api.redrawRows();  //리로드
     
       });

      }

//삭제
checkdelete(){

  this.agGrid.api.getSelectedNodes().forEach(element => {
   // alert(element.rowIndex);
    this.statmentArray["dailyAttdList"][element.rowIndex].status="deleted"
   this.agGrid.api.redrawRows();  //리로드
  });

}




      //저장버튼
      savebtn(){
       var batchData=[]; 
       this.agGrid.rowData.forEach(element => {
          //console.log(element);
          if(element.status!="normal"){
           // console.log(element);
            batchData.push(element);
          }
        });
       // alert(batchData);
        this.hrService.transaction("attendance/batchDailyAttd.do",JSON.stringify(batchData),"").subscribe();
        this.agGrid.api.redrawRows();  //리로드

        this.findStatment(this.basicDate);
  }

//날짜
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


  constructor(http:HttpClient) { 
  	this.http=http;
    this.hrService = new HrService(this.http);
  }

  ngOnInit() {
    
  }

//0붙이기
  leadingZeros(date, num) {
    var zero = '';
    date = date.toString();
   
    if (date.length < num) {
     for (var i = 0; i < num - date.length; i++)
      zero += '0';
    }
    return zero + date;
 }

   
  //그리드
 private statmentArray=[];

 columnDefs=[
  {headerName: '선택', width: 80,checkboxSelection: true},
	{headerName: '사원코드', field: 'empCode', width: 100 },
  {headerName: '사원명', field: 'empName', width: 100},
  {headerName: '기준일', field: 'basicDay', width: 100},
  {headerName: '시간', field: 'time', width: 100},
  {headerName: '근태구분', field: 'attdTypeCode', width: 100},
  {headerName: '비용', field: 'cost', width: 100},
	{headerName: '승인상태', field: 'approvalStatus', width: 100},
  {headerName: '사유', field: 'cause', width: 100},
  {headerName: 'status', field: 'status', width: 100}
	]

}
