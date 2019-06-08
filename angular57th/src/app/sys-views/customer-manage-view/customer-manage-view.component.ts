import { Component, OnInit, ViewChild, Inject } from '@angular/core';

import { FormGroup } from '@angular/forms';
import { AgGridNg2 } from 'ag-grid-angular';
import { HttpClient } from '@angular/common/http';
import { dataStore } from 'src/app/common/redux/core/code.store';
import { Store } from 'redux';
import { dataState } from 'src/app/common/redux/core/code.state';
import * as InsertActions from 'src/app/common/redux/core/codedata.action';
import { SysService } from 'src/app/common/svc/sys.service';

@Component({
  selector: 'app-customer-manage-view',
  templateUrl: './customer-manage-view.component.html',
  styleUrls: ['./customer-manage-view.component.css']
})

export class CustomerManageViewComponent implements OnInit {
  @ViewChild('agGrid') agGrid: AgGridNg2;

  title = 'angularTest';
  businessCode:any[];
  deptCode:string;
  empno:string;
  pw:string;
  
  //sysService:SysService;
  firstFormGroup:FormGroup;
    ////////////////
  
    
  constructor(private sysService:SysService,public http:HttpClient, @Inject(dataStore) private store: Store<dataState>)
   { 
    this.sysService=new SysService(http);
     store.subscribe(() => this.readState());
    this.readState();
   }
   
  public dataArray2 = [];
   readState() {
   const state: dataState = this.store.getState() as dataState;
   this.dataArray2=state.code;
   console.log(this.dataArray2);
  }
  redux(){
    this.store.dispatch(InsertActions.insert(this.dataArray2));
  }
  sendVariable(){
    localStorage.setItem("pw",this.pw);
    this.sysService.transaction("test.do","","empno="+this.empno).subscribe()
  }
  submit(){
    console.log(this.firstFormGroup.value);
  }

  ngOnInit() {

  }

ngDoCheck(){

}
  getSelectedRows() {
    const selectedNodes = this.agGrid.api.getSelectedNodes();
    console.log(selectedNodes);
    const selectedData = selectedNodes.map( node => node.data);
    console.log(JSON.stringify(selectedData));
   
}

onCellClick(params:any){
  const selectedNodes = this.agGrid.api.getSelectedNodes();
  const selectedData = selectedNodes.map( node => node.data);
alert(selectedNodes);
}

addRows(){
  var length=this.dataArray2.length;
 // var newData:string;
  var newData={chk: "0", code: "test", codeDetailList: null, codeGroup: "test",  codeName: "test",  editStatus: "Y",  status: "inserted"};
  this.dataArray2["codeList"].push(newData);
  this.agGrid.api.setRowData(this.dataArray2["codeList"]);
  console.log(this.dataArray2["codeList"]);
  
  }

  editRow(){
    this.dataArray2["codeList"][0].status="updated";
    this.dataArray2["codeList"][1].status="updated";
    this.agGrid.api.redrawRows();
  }

  getEditedData(){
   // console.log(this.agGrid.rowData);
   var batchData=[]; 
   this.agGrid.rowData.forEach(element => {
      //console.log(element);
      if(element.status!="normal"){
       // console.log(element);
        batchData.push(element);
      }
    });
    console.log(batchData);
    this.sysService.transaction("batchCode.do",JSON.stringify(batchData),"").subscribe();
  }

  deleteRow(){
    
    this.agGrid.api.getSelectedNodes().forEach(element => {
   //   alert(element.rowIndex);
      this.dataArray2["codeList"][element.rowIndex].status="deleted"
    });
    this.agGrid.api.redrawRows();
   // alert(this.agGrid.getRowClass);
   
  }
getRowStyle = function(params) {
    if (params.data.status == 'updated') {
        return { background: 'blue' }
    }else if(params.data.status == 'deleted') {
      return { background: 'gray' }
  }else if(params.data.status == 'inserted') {
    return{ background: 'yellow' }
};
  }
  columnDefs = [
    {headerName: 'code', field: 'code', width: 80,checkboxSelection: true ,onCellClicked:params=>{this.onCellClick(params)}},
    {headerName: 'codeGroup', field: 'codeGroup'},
    {headerName: 'codeName', field: 'codeName', editable:true},
    {headerName: 'editStatus', field: 'editStatus'},
    {headerName: 'status', field: 'status'}
  ];

}
