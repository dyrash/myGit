import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SysService } from 'src/app/common/svc/sys.service';
import { AgGridNg2 } from 'ag-grid-angular';

@Component({
  selector: 'app-cutomer-view',
  templateUrl: './cutomer-view.component.html',
  styleUrls: ['./cutomer-view.component.css']
})
export class CutomerViewComponent implements OnInit {
  @ViewChild('customerGrid') agGrid: AgGridNg2;
  private http:HttpClient;
  private sysService:SysService;

  constructor(http:HttpClient) {
    this.http=http;
    this.sysService=new SysService(this.http);
   }

  private customerListArray=[];
  ngOnInit() {
    this.sysService.transaction("findCustomerList.do","","").subscribe((result : any[])=>{
      this.customerListArray = result;
      console.log(this.customerListArray);
     
      });

  }

  columnDefs = [
    {headerName: '사업자등록번호', field: 'businessLicenseNumber', checkboxSelection: true, editable:true },
    {headerName: 'customerBusinessConditions', field: 'customerBusinessConditions', editable:true},
    {headerName: 'customerBusinessItems', field: 'customerBusinessItems', editable:true},
    {headerName: 'customerCode', field: 'customerCode'},
    {headerName: 'customerDetailAddress', field: 'customerDetailAddress', editable:true},
    {headerName: 'customerFaxNumber', field: 'customerFaxNumber', editable:true},
    {headerName: 'customerName', field: 'customerName', editable:true},
    {headerName: 'customerTelNumber', field: 'customerTelNumber', editable:true},
    {headerName: 'customerType', field: 'customerType', editable:true},
    {headerName: 'customerZipCode', field: 'customerZipCode', editable:true},
    {headerName: 'customerBasicAddress', field: 'customerBasicAddress', editable:true},
    {headerName: 'socialSecurityNumber', field: 'socialSecurityNumber', editable:true},
    {headerName: 'workplaceCode', field: 'workplaceCode'},
    {headerName: 'status', field: 'status'}
  ];


  onCellClick(params:any){
  alert(params);
  }

  addRows(){
   var newData={
    customerCode:"PTN-99",
    workplaceCode:"BRC-01",
    customerName:"",
    customerType:"",
    customerCeo:"",
    businessLicenseNumber:"",
    socialSecurityNumber:"",
    customerBusinessConditions:"",
    customerBusinessItems:"",
    customerZipCode:"",
    customerBasicAddress:"",
    customerDetailAddress:"",
    customerTelNumber:"",
    customerFaxNumber:"",
    status:"inserted"    
    };
   this.customerListArray["customerList"].push(newData);
   this.agGrid.api.setRowData(this.customerListArray["customerList"]);
   console.log(this.customerListArray["customerList"]);
  }

  deleteRow(){
    
    this.agGrid.api.getSelectedNodes().forEach(element => {
       this.customerListArray["customerList"][element.rowIndex].status="deleted"
    });
    this.agGrid.api.redrawRows();
   
  }
 batchData(){
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
     this.sysService.transaction("batchCustomer.do",JSON.stringify(batchData),"").subscribe();
   }
 
}
