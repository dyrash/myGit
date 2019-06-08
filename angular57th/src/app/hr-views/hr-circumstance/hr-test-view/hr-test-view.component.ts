import { Component, OnInit, Inject } from '@angular/core';
import { HrService } from 'src/app/common/svc/hr.service';
import { HttpClient } from '@angular/common/http';
import { dataStore } from 'src/app/common/redux/core/code.store';
import { Store } from 'redux';
import { dataState } from 'src/app/common/redux/core/code.state';

@Component({
  selector: 'app-hr-test-view',
  templateUrl: './hr-test-view.component.html',
  styleUrls: ['./hr-test-view.component.css']
})
export class HrTestViewComponent implements OnInit {
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
}
