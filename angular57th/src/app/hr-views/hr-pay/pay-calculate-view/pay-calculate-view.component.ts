import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { AgGridNg2 } from 'ag-grid-angular';
import { HttpClient } from '@angular/common/http';
import { dataStore } from 'src/app/common/redux/core/code.store';
import { Store } from 'redux';
import { dataState } from 'src/app/common/redux/core/code.state';
import { HrService } from 'src/app/common/svc/hr.service';

@Component({
  selector: 'app-pay-calculate-view',
  templateUrl: './pay-calculate-view.component.html',
  styleUrls: ['./pay-calculate-view.component.css']
})
export class PayCalculateViewComponent implements OnInit {
  @ViewChild('paycalculateGrid') paycalculateGrid: AgGridNg2;
  hrsvc:HrService;
  dataArray=[];
  payDayList=[];
  payDay :string = '';
  salaryInputList=[];
  paydeductionList=[];
  paycalculateColumnDefs = [
    {headerName: '사원번호', field: 'empCode',checkboxSelection: true, editable:true, width: 100},
    {headerName: '이름', field: 'empName', editable:true, width: 100},
    {headerName: '직급', field: 'positionCode', editable:true, width: 100},
    {headerName: '부서', field: 'deptCode', editable:true, width: 100},
    {headerName: '사업장', field: 'businessPlaceCode', editable:true, width: 100}
  ];

  paydeductionColumnDefs = [
    {headerName: 'payDeductionSeq', field: 'payDeductionSeq',checkboxSelection: true, editable:true, width: 100},
    {headerName: 'price', field: 'price', editable:true, width: 100},
    {headerName: 'payDeductionItemCode', field: 'payDeductionItemCode', editable:true, width: 100},
    {headerName: 'payDeductionItemName', field: 'payDeductionItemName', editable:true, width: 100},
    {headerName: 'empCode', field: 'empCode', editable:true, width: 100},
    {headerName: 'paymentDate', field: 'paymentDate', editable:true, width: 100},
    {headerName: 'payDeductionTypeCode', field: 'payDeductionTypeCode', editable:true, width: 100}


  ];
    salaryInputColumnDefs = [
    {headerName: 'paymentDate', field: 'paymentDate',checkboxSelection: true, editable:true, width: 100},
    {headerName: 'empCode', field: 'empCode', editable:true, width: 100},
    {headerName: 'salaryTypeCode', field: 'salaryTypeCode', editable:true, width: 100},
    {headerName: 'totalSalary', field: 'totalSalary', editable:true, width: 100},
    {headerName: 'totalDeductionPrice', field: 'totalDeductionPrice', editable:true, width: 100},
    {headerName: 'deptCode', field: 'deptCode', editable:true, width: 100},
    {headerName: 'payDeductionItemName', field: 'payDeductionItemName', editable:true, width: 100},
    {headerName: 'hireDate', field: 'hireDate', editable:true, width: 100},
    {headerName: 'retireDate', field: 'retireDate', editable:true, width: 100},
    {headerName: 'chinePayments', field: 'chinePayments', editable:true, width: 100},
    ];

  payDayColumnDefs = [
    {headerName: 'paymentDate', field: 'paymentDate'},
    {headerName: 'jikjongCode', field: 'jikjongCode'},
    {headerName: 'targetChoice', field: 'targetChoice'},
    {headerName: 'sameTimeSelection', field: 'sameTimeSelection'},
    {headerName: 'payType', field: 'payType'},
    {headerName: 'salaryTypeCode', field: 'salaryTypeCode'},
    {headerName: 'note', field: 'note'},
    {headerName: 'slipRegistStatus', field: 'slipRegistStatus'},
    {headerName: 'status', field: 'status'}
  ];

  constructor(private http:HttpClient,@Inject(dataStore) private store: Store<dataState>) {
   this.hrsvc=new HrService(http);
   }

  ngOnInit() {
    this.findEmp();
  }
  findEmp(){
    this.hrsvc.transaction("emp/findEmployeeList.do","","").subscribe((result : any[])=>{
      this.dataArray = result;
      console.log(this.dataArray);
    });
  }
  findPayDay(){
    this.hrsvc.transaction("circumstance/findSalPaymentDateList.do","","inputedYearMonth=201903").subscribe((result : any[])=>{
      this.payDayList = result["salPaymentsDateList"];
      console.log(this.payDayList); 
    });
  }
  payDayRowClicked(params){
  this.payDay=params.data.paymentDate;
  }
payCalculate(){
  this.hrsvc.transaction("pay/payCalculateProcess.do","","paymentDate=20190320 standardDate=201903").subscribe((result : any[])=>{
    this.salaryInputList = result["salaryInputList"];
  
    console.log(this.salaryInputList); 
  });
}
payGridClicked(params){
  const selectedData =params.data;
  this.paydeductionList= selectedData["payDeductionList"];
 
  params.api.redrawRows();
  }
}
