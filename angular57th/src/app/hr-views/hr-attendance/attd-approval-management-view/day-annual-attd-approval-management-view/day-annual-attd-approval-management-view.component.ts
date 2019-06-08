import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AgGridNg2 } from 'ag-grid-angular';
import { HrService } from 'src/app/common/svc/hr.service';

@Component({
  selector: 'app-day-annual-attd-approval-management-view',
  templateUrl: './day-annual-attd-approval-management-view.component.html',
  styleUrls: ['./day-annual-attd-approval-management-view.component.css']
})
export class DayAnnualAttdApprovalManagementViewComponent implements OnInit {
  private http:HttpClient;  
  @ViewChild('statmentGrid') agGrid: AgGridNg2;
  hrService : HrService;
  private statmentArray=[];
  model;
  model2;

deptCode;
approvalStatus;
fromDate;
toDate;

//조회버튼을 누르면
    findStatment(model:string,model2:string){
      
     this.fromDate=this.createDate(model);
     this.toDate=this.createDate(model2);
    // alert(this.fromDate);
    // alert(this.toDate);
    // alert(this.deptCode);
    // alert(this.approvalStatus);

     this.hrService.transaction("attendance/findDayAnnualListByCondition.do","","approvalStatus="+this.approvalStatus+" fromDate="+this.fromDate+" toDate="+this.toDate+" deptCode="+this.deptCode).subscribe((result : any[])=>{
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
 // alert(selectedNodes);
  const selectedData = selectedNodes.map( node => node.data);
  alert(JSON.stringify(selectedData));

}



  //선택승인변경
  checkApproval(){
 
    this.agGrid.api.getSelectedNodes().forEach(element => {
        // alert(element.rowIndex);
         this.statmentArray["dayAnnualList"][element.rowIndex].status="updated"

        if(status!="nomal") {
          if(this.statmentArray["dayAnnualList"][element.rowIndex].approvalStatus=="N"){
         this.statmentArray["dayAnnualList"][element.rowIndex].approvalStatus="Y"
          }else{
            this.statmentArray["dayAnnualList"][element.rowIndex].approvalStatus="N"
          }
        }
      //  alert(this.statmentArray["dayAnnualList"][element.rowIndex].status);
      //  alert(this.statmentArray["dayAnnualList"][element.rowIndex].approvalStatus);
      //  alert(this.statmentArray["dayAnnualList"][element.rowIndex].cost);
        this.agGrid.api.redrawRows();  //리로드
     
       });

      }

//삭제
checkdelete(){

  this.agGrid.api.getSelectedNodes().forEach(element => {
  //  alert(element.rowIndex);
    this.statmentArray["dayAnnualList"][element.rowIndex].status="deleted"
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
        this.hrService.transaction("attendance/batchDayAnnual.do",JSON.stringify(batchData),"").subscribe();
        this.agGrid.api.redrawRows();  //리로드

        this.findStatment(this.model,this.model2);
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
  
  
    constructor(http:HttpClient) { 
      this.http=http;
      this.hrService = new HrService(this.http);
    }
  
    ngOnInit() {
      
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
  
     
     //그리드


 columnDefs=[
  {headerName: '선택', width: 80,checkboxSelection: true},
	{headerName: '사원코드', field: 'empCode', width: 100 },
  {headerName: '사원명', field: 'empName', width: 100},
  {headerName: '시작일', field: 'startDate', width: 100},
  {headerName: '종료일', field: 'endDate', width: 100},
  {headerName: '일수', field: 'days', width: 100},
	{headerName: '승인상태', field: 'approvalStatus', width: 100},
  {headerName: '상세사유', field: 'detailCause', width: 100},
  {headerName: 'status', field: 'status', width: 100}
	]
    
}
