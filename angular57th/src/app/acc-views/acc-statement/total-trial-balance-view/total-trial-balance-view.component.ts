import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AgGridNg2 } from 'ag-grid-angular';
import { AccService } from 'src/app/common/svc/acc.service';

@Component({
  selector: 'app-total-trial-balance-view',
  templateUrl: './total-trial-balance-view.component.html',
  styleUrls: ['./total-trial-balance-view.component.css']
})
export class TotalTrialBalanceViewComponent implements OnInit {
  private http:HttpClient;
  model;
  @ViewChild('statmentGrid') agGrid: AgGridNg2;
  accService : AccService;
  constructor(http:HttpClient) { 
    this.http=http;
    this.accService = new AccService(this.http);
  }
 
  ngOnInit() {
  }
  private statmentArray=[];
  findStatment(){

console.log(this.model);
var a=this.model.year;
var b=this.getFormatDate(this.model.month);
var c=this.getFormatDate(this.model.day);
var approvalDate=a+b+c;
alert(approvalDate);

    this.accService.transaction("statement/totalTrialBalance/findTotalTrialBalanceList.do","","approvalDate="+approvalDate).subscribe((result : any[])=>{
      this.statmentArray = result;
      console.log(this.statmentArray);
      });
       
  }
  getFormatDate(monthday){
    monthday = monthday >= 10 ? monthday : '0' + monthday;
    return  monthday;
  }
  columnDefs=[

    {
      headerName: "차변",
      groupId: "medalsGroup",
      children: [
        {
          headerName: "잔액",
          field: "leftDebtorBalance"
        },
        {
          headerName: "      합계",
          field: "leftDebtorSum"           
        },
       ]},
    {headerName: '계정과목', field: 'accountName' },


    {
      headerName: "대변",
      groupId: "medalsGroup",
      children: [
        {
          headerName: "잔액",
          field: "rightCreditsSum"
          
        },
        {
          headerName: "합계",
          field: "rightCreditsBalance"
        },
       ]},
  ]

}



//, width: 80
//, colStyle:{"textAlign":"center"}
// {
//   headerName: "Medals",
//   groupId: "medalsGroup",
//   children: [
//     {
//       headerName: "Gold",
//       field: "gold",
//       type: "medalColumn"
//     },
//     {
//       headerName: "Silver",
//       field: "silver",
//       type: "medalColumn"
//     },
//     {
//       headerName: "Bronze",
//       field: "bronze",
//       type: "medalColumn"
//     }]}