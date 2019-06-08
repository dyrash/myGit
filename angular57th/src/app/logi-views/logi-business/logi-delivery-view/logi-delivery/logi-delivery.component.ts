import { Component, ViewChild, Query, OnInit, Inject } from '@angular/core';
import { AgGridNg2 } from 'ag-grid-angular';
import { LogiService } from 'src/app/common/svc/logi.service';
import { HttpClient } from '@angular/common/http';
import { dataStore } from 'src/app/common/redux/core/code.store';
import { Store } from 'redux';
import { dataState } from 'src/app/common/redux/core/code.state';
import * as InsertActions from 'src/app/common/redux/core/codedata.action';

interface data{
  deliveryNo: string;
  contractDetailNo: string;
  warehouseCode: string;
  itemCode: string;
  itemName: string;
  unitOfDeliveryResult: string;
  deliveryDate: string;
  deliveryAmount: string;
  description?: string;
  customerCode?: string;
  status: string;
}

@Component({
  selector: 'app-logi-delivery',
  templateUrl: './logi-delivery.component.html',
  styleUrls: ['./logi-delivery.component.css']
})


export class LogiDeliveryComponent implements OnInit{
  @ViewChild('contractGrid') contractGrid: AgGridNg2;
  @ViewChild('contractDetailGrid') contractDetailGrid: AgGridNg2;
  @ViewChild('deliveryGrid')  deliveryGrid: AgGridNg2;
  @ViewChild('stockGrid')  stockGrid: AgGridNg2;
  selRow:number=0;
  private contractArray=[];
  private contractDetailList=[];
  private deliveryList=[]; 
  public stockArray=[];
  public dataArray = [];
  logiService:LogiService;
  constructor(public http:HttpClient,@Inject(dataStore) public store: Store<dataState>) { 
    store.subscribe(() => this.readState());
    //this.readState();
    this.logiService=new LogiService(http);
  }
 

   contractColumnDefs = [
    {headerName: "-------------------------------------------------------------수주목록-----------------------------------------------------------",
    groupId: "medalsGroup",
    children:[
    {headerName: '수주번호', field: 'contractNo'},
    {headerName: '견적번호', field: 'estimateNo',hide:true},
    {headerName: '수주형식', field: 'contractType',hide:true},
    {headerName: '거래처', field: 'customerCode'},
    {headerName: '수주일', field: 'contractDate'},
    {headerName: '수주요청자', field: 'contractRequester'},
    {headerName: '비고', field: 'description'},
    {headerName: '납품상태', field: 'deliveryResultStatus'},
    {headerName: '전표등록상태', field: 'slipRegistStatus',hide:true},
    {headerName: 'status', field: 'status',hide:true}]}
  ];

  contractDetailColumnDefs = [
    {headerName: "--------------------------------------------------------수주상세목록------------------------------------------------------",
    groupId: "medalsGroup",
    children:[
    {headerName: '수주번호', field: 'contractNo',hide:true},
    {headerName: '수주상세번호', field: 'contractDetailNo'},
    {headerName: '품목코드', field: 'itemCode'},
    {headerName: '제품명', field: 'itemName'},
    {headerName: '납기일', field: 'dueDateOfContract'},
    {headerName: '수량', field: 'contractAmount'},
    {headerName: '단위', field: 'unitPriceOfContract'},
    {headerName: '합계가격', field: 'sumPriceOfContract'},
    {headerName: '납품상태', field: 'deliveryStatus'},
    {headerName: '비고', field: 'description',editable:true},
    {headerName: '납품잔량', field: 'deliveryRemain',hide:true},
    {headerName: '전표등록상태', field: 'slipRegistStatus',hide:true},
     {headerName: 'status', field: 'status',hide:true}]}
  ];

  deliveryColumnDefs = [
    {headerName: "-------------------------------------------------------------납품목록-----------------------------------------------------------",
    groupId: "medalsGroup",
    children:[
    {headerName: '납품번호', field: 'deliveryNo',hide:true},
    {headerName: '수주상세번호', field: 'contractDetailNo'},
    {headerName: '품목코드', field: 'itemCode'},
    {headerName: '제품명', field: 'itemName'},
    {headerName: '창고', field: 'warehouseCode',hide:true},
    {headerName: '단위', field: 'unitOfDeliveryResult'},
    {headerName: '납품일', field: 'deliveryDate',hide:true},
    {headerName: '납품상태', field: 'deliveryStatus',hide:true},
    {headerName: '수량', field: 'deliveryAmount'},
    {headerName: '납품잔량', field: 'deliveryRemain',hide:true},
    {headerName: '비고', field: 'description'},
    {headerName: '거래처', field: 'customerCode'},
    {headerName: 'status', field: 'status',hide:true }]}
  ];

  stockColumnDefs = [  {headerName: "-------------------------------------------------------------재고목록-----------------------------------------------------------",
  groupId: "medalsGroup",
  children:[
    {headerName: '제품코드', field: 'itemCode'},
    {headerName: '제품명', field: 'itemName'},
    {headerName: '재고수량', field: 'stockAmount'},
    {headerName: '안전재고', field: 'safetyAllowanceAmount'},
    {headerName: '창고', field: 'warehouseCode',hide:true},
    {headerName: '단위', field: 'unitOfStock'},
    {headerName: '설명', field: 'description'},
    {headerName: 'status', field: 'status',hide:true}]}
  ];
  ngOnInit() {
    this.findContract();
    this.findStock();
    this.deliveryList=[];

   }
   ngAfterViewInit(){
    this.contractGrid.api.sizeColumnsToFit();  
    this.contractDetailGrid.api.sizeColumnsToFit();  
    this.deliveryGrid.api.sizeColumnsToFit();  
    this.stockGrid.api.sizeColumnsToFit();  
    this.setFilter();
  } 
   readState() {

  }

  findStock(){
    this.logiService.transaction("purchase/findStockList.do","","").subscribe((result : any[])=>{
      this.stockArray = result["stockList"];
      console.log(this.contractArray);
      this.store.dispatch(InsertActions.insert(this.dataArray));
      this.setFilter();
      this.stockGrid.api.redrawRows();
      });
  }
  testMap:Map<string,any>;
  findContract(){
      // var mapper=new mapper();
        this.logiService.transaction("business/findContractList.do","","fromDate=unDeliveried toDate=0").subscribe((result : any[])=>{
        this.contractArray = result["contractList"];
        console.log(this.contractArray);
        this.contractGrid.api.redrawRows();
        //this.testMap=mapper.jsonToMap(result,"contractList");
     
  });

}
 
   
  onContractCellClick(params){
    console.log(params);
    const selectedData =params.data;
    this.contractDetailGrid.api.setRowData(selectedData["contractDetailList"]);
    this.setFilter();
    console.log(this.contractDetailGrid);
    this.contractDetailGrid.api.redrawRows();

  }
  onDetailCellClick(params){
//    console.log(params);
    this.stockGrid.api.forEachNode((node,index)=>{
      //console.log(node);
      //console.log(index);
      if(node.data.itemCode==params.data.itemCode){
        //alert(node.data.itemCode);
        var remainStock=node.data.stockAmount-params.data.contractAmount;
        if(remainStock>=0){
          node.data.stockAmount=remainStock;
          params.data.status="updated";
          params.data.deliveryStatus="Y";
          this.contractDetailGrid.api.redrawRows();
      
          var remain=true;
          console.log(this.contractGrid.api.getSelectedRows()[0].contractDetailList);
          
          this.contractGrid.api.getSelectedRows()[0].contractDetailList.forEach(element => {
       
          if(element.deliveryStatus!="Y"){
               remain=false;
            }
           
           });
          if(remain){
         //   alert("!");
            this.contractGrid.api.getSelectedRows()[0].status="updated";
            this.contractGrid.api.getSelectedRows()[0].deliveryResultStatus="Y";
            this.contractGrid.api.redrawRows();
          }
          console.log(this.contractGrid.api.getSelectedRows()[0]);
          var newDelivery:data={
            deliveryNo: "저장후할당"
            , contractDetailNo: params.data.contractDetailNo
            , warehouseCode: "WH-01"
            , itemCode: params.data.itemCode
            , itemName: params.data.itemName
            , unitOfDeliveryResult: "EA"
            , deliveryDate: "저장후할당"
            ,	deliveryAmount: params.data.contractAmount
            , description: ""
            , customerCode: this.contractGrid.api.getSelectedRows()[0].customerCode
            , status: "inserted"
          };
          this.contractGrid.api.getSelectedRows()[0].status="updated";
          this.deliveryList.push(newDelivery);
          this.deliveryGrid.api.setRowData(this.deliveryList);
          console.log(this.deliveryList);
          this.setFilter();
        }else{
          alert("수량부족");
        }
      
        node.data.status="updated";
        this.stockGrid.api.redrawRows();
      }
    });

}
setFilter(){
         //필터주소취득
         var deliveryStatusFilterComponent = this.contractDetailGrid.gridOptions.api.getFilterInstance('deliveryStatus');
         var contractDeliveryStatusFilterComponent = this.contractGrid.gridOptions.api.getFilterInstance('deliveryResultStatus');
         
         // 필터모델셋팅
         deliveryStatusFilterComponent.setModel({
             type:'notEqual',
             filter:'Y'
         });
         contractDeliveryStatusFilterComponent.setModel({
           type:'notEqual',
           filter:'Y'
         });
         //적용한 필터 작동
         this.contractDetailGrid.gridOptions.api.onFilterChanged();
         this.contractGrid.gridOptions.api.onFilterChanged();
         //들어간 필터모델확인해보기
         
         var model = deliveryStatusFilterComponent.getModel();
         
         console.log(model);
         
}
  batchDelivery(){
    var batchData={};
    var contractData=[];
    var stockData=[];
    var deliveryData=[];
    this.contractGrid.rowData.forEach(element => {
      //console.log(element);
      if(element.status!="normal"){
       // console.log(element);
       contractData.push(element);
 
      }
    });
    console.log(batchData);
    this.stockGrid.rowData.forEach(element => {
      //console.log(element);
      if(element.status!="normal"){
       // console.log(element);
       stockData.push(element);
      }
    });
    console.log(batchData);
    this.deliveryGrid.rowData.forEach(element => {
      //console.log(element);
      if(element.status!="normal"){
       // console.log(element);
       deliveryData.push(element);
      }
    });
    batchData["contractData"]=contractData;
    batchData["stockData"]=stockData;
    batchData["deliveryData"]=deliveryData;
    console.log(batchData);
    //this.store.dispatch(InsertActions.insert(this.stockArray))
    this.logiService.transaction("business/registDeliveryResult.do",JSON.stringify(batchData),"").subscribe(()=>{
      alert("저장완료");
      this.ngOnInit();
    });
  }
}
