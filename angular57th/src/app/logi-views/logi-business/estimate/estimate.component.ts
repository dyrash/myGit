import { Component, OnInit, ViewChild, Inject } from '@angular/core';

import { AgGridNg2 } from 'ag-grid-angular';
import { HttpClient } from '@angular/common/http';

import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
import { SysService } from 'src/app/common/svc/sys.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { LogiService } from 'src/app/common/svc/logi.service';
import { toggle } from 'src/app/common/shared/animations/component-animation/toggle';
import { GridDatePickerComponent } from 'src/app/common/shared/date-pickers/grid-date-picker/grid-date-picker.component';
import { dataStore } from 'src/app/common/redux/core/code.store';
import { Store } from 'redux';
import { dataState } from 'src/app/common/redux/core/code.state';
declare let $: any; //제이쿼리사용가능

interface data{
  estimateDetailNo: string;
  estimateNo: string;
  itemCode: string;
  itemName: string;
  unitOfEstimate: string;
  dueDateOfEstimate: string;
  estimateAmount: string;
  unitPriceOfEstimate: string;
  sumPriceOfEstimate: string;
  status: string;
}

@Component({
  selector: 'app-estimate',
  templateUrl: './estimate.component.html',
  styles: [`
    .custom-day {
      text-align: center;
      padding: 0.185rem 0.25rem;
      display: inline-block;
      height: 2rem;
      width: 2rem;
    }
    .custom-day.focused {
      background-color: #e6e6e6;
    }
    .custom-day.range, .custom-day:hover {
      background-color: rgb(2, 117, 216);
      color: white;
    }
    .custom-day.faded {
      background-color: rgba(2, 117, 216, 0.5);
    }
  `],
  animations: [
            toggle
  ]
})
export class EstimateComponent implements OnInit {
  @ViewChild('estimateGrid') estimateGrid: AgGridNg2;
  @ViewChild('estimateDetailGrid') estimateDetailGrid: AgGridNg2;
  @ViewChild('itemDialogGrid') itemDialogGrid: AgGridNg2;
  @ViewChild('content') content:NgbModal;

  hoveredDate: NgbDate;

  fromDate: NgbDate;
  toDate: NgbDate;

  title = 'EstimateForm';
  logiService:LogiService;
  sysService:SysService
  counter: number;

  itemCode: string = '';
  itemName: string = '';
  unitOfStock: string = '';
  unitPriceOfEstimate: string = '';
  empCode="1111";
  //생성자
  constructor(public http:HttpClient, private modalService: NgbModal, calendar: NgbCalendar,@Inject(dataStore) public store: Store<dataState>) {
    store.subscribe(() => this.readState());
    this.fromDate = calendar.getToday();
    this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);

    this.logiService=new LogiService(http);
    this.sysService=new SysService(http);
 

   }

  private dataArray = [];

//달력 function
get stateName() {
  return this.datePickerCheck ? 'show' : 'hide'
}
 datePickerCheck=false;

dateSlide_OnClick(event){
  this.datePickerCheck=event.checked;
   console.log(event.checked);
}

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {

    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  
  } //달력 function 종료

  public customerList=[];
  ngOnInit() {
    this.sysService.transaction("findCustomerList.do","","").subscribe((result : any[])=>{
      this.customerList 
      result["customerList"].forEach(element => {
        console.log(element["customerName"]);
        this.customerList.push(element["customerName"]);
      });
      console.log(this.customerList);
      });
  }
  ngAfterViewInit(){
    this.estimateGrid.api.sizeColumnsToFit();  
    this.estimateDetailGrid.api.sizeColumnsToFit();  
  } 
  //날자 function
  getFormatDate(monthday){
    monthday = monthday >= 10 ? monthday : '0' + monthday;
    return  monthday;
  }

  //견적조회
  public fromDays: string;
  public toDays: string;
  findEstimateList() {
    var fromYear=String(this.fromDate.year);
    var fromMonth=this.getFormatDate(String(this.fromDate.month));
    var fromDay=this.getFormatDate(String(this.fromDate.day));
    
    this.fromDays=fromYear+fromMonth+fromDay;
    
    var toYear=String(this.toDate.year);
    var toMonth=this.getFormatDate(String(this.toDate.month));
    var toDay=this.getFormatDate(String(this.toDate.day));

    this.toDays=toYear+toMonth+toDay;

    const selectedNodes = this.estimateGrid.api.getSelectedNodes();
    console.log(selectedNodes);
    const selectedData = selectedNodes.map( node => node.data);
    console.log(JSON.stringify(selectedData));

    this.Http();
}

Http(){
  this.logiService.transaction("business/findEstimateList.do","","fromDate="+this.fromDays+" toDate="+this.toDays+"").subscribe((result : any[])=>{
  this.dataArray = result;
  console.log(this.dataArray);
  });
    
}
  
//견적추가
public estimateNum: string;
private estimateArray={};
addEstimate(){
  this.sysService.transaction("findSequenceNo.do","","findSeq=ESTI_SEQ").subscribe((result : any[])=>{
  //this.dataArray = result;
   var sqno=result;
   this.estimateNum="ESTI"+sqno["squenceNo"];
  // alert(this.estimateNum);
    console.log(this.dataArray);
    var estimateList=[];
    var newData={estimateNo:this.estimateNum, customerCode:"", estimateDate:"", contractStatus:"N", estimateRequester:"", effectiveDate:"", personCodeInCharge:this.empCode, status: "inserted"};
    estimateList.push(newData);
    this.estimateArray={"estimateList":estimateList};
    this.estimateGrid.api.setRowData(this.estimateArray["estimateList"]);
    console.log(this.estimateArray["estimateList"]);
    });
  }

  //견적 삭제
  deleteEstimate(){
    this.estimateGrid.api.getSelectedNodes().forEach(element => {
      this.dataArray["estimateList"][element.rowIndex].status="deleted"
    });
    this.estimateGrid.api.redrawRows();
  }

  //저장
  saveEstimate(){
    // console.log(this.agGrid.rowData);
    var batchData=[];
    this.estimateGrid.api.forEachNode((node,index)=> {
      //console.log(node);
      if(node.data.status=="inserted"){
        var estimateNodeDetailList = [];
        var estiNode = node.data;
        console.log(estiNode);
        this.estimateDetailGrid.api.forEachNode((denode,index)=> {
          estimateNodeDetailList.push(denode.data);
        });
        node.data["estimateDetailList"]=estimateNodeDetailList;
         batchData.push(node.data);
       }
     });
     console.log(batchData);
     this.logiService.transaction("business/registerEstimateDetail.do",JSON.stringify(batchData),"").subscribe(()=>{
      this.findEstimateList(); 
      this.estimateDetailList=[];
      alert("저장성공");
      this.estimateGrid.api.redrawRows();
      this.estimateDetailGrid.api.redrawRows();
     });
    }
 
  //견적상세 조회
  public estimateDetailList=[];
  onCellClick(params:any){
  var estimatenum =  params.data;
  this.estimateDetailList = estimatenum["estimateDetailList"];
  this.logiService.transaction("business/findEstimateDetailList.do","","estimateNo="+estimatenum+"").subscribe();
  this.estimateDetailGrid.api.redrawRows();
  }

  //견적상세추가
  private estimateDetailArray={};
  public num = 1;
  public estimateDetailNum: string;
  addEstimateDetail(){
      this.estimateDetailNum=this.estimateNum+"-0"+this.num++;
      alert(this.estimateDetailNum);
       console.log(this.dataArray);
       var estimateDetailList=[];
       var newData:data={estimateDetailNo:this.estimateDetailNum, estimateNo:this.estimateNum, itemCode:"", itemName:"", unitOfEstimate:"EA", dueDateOfEstimate:"", estimateAmount:"", unitPriceOfEstimate:"", sumPriceOfEstimate:"", status: "inserted"};//
  //     alert(this.estimateNum);
       this.estimateDetailList.push(newData);
       this.estimateDetailGrid.api.setRowData(this.estimateDetailList);
       console.log(this.estimateDetailList);
  }
  

  //아이템 다이얼 로그
  closeResult: string;
  onItemCellClick(params){
    this.logiService.transaction("purchase/findItemList.do","","").subscribe((result : any[])=>{
      var itemList=[]
      result["itemList"].forEach(element => {
        if(element.itemClassification!="IT-MA"){
          itemList.push(element);
        }
      });
      this.dataArray = itemList;
      console.log(this.dataArray);
      });
    this.modalService.open(this.content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      params.data.itemCode=this.itemCode;
      params.data.itemName=this.itemName;
      params.data.unitOfStock=this.unitOfStock;
      params.data.unitPriceOfEstimate=this.unitPriceOfEstimate;
      this.estimateDetailGrid.api.redrawRows();
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  //다이얼로그에 필요
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
  //그리드 색상 function
  getRowStyle = function(params) {
    if (params.data.status == 'updated') {
        return { background: 'blue' }
    }else if(params.data.status == 'deleted') {
      return { background: 'gray' }
    }else if(params.data.status == 'inserted') {
     return{ background: 'yellow' }
    };
  }

  //견적 컬럼
  estimateColumnDefs = [
    {headerName: '견적일련번호', field: 'estimateNo',checkboxSelection: true,onCellClicked:params=>{this.onCellClick(params)}},
    {headerName: '거래처코드', field: 'customerCode', editable:true, cellEditor: 'agSelectCellEditor'
    ,cellEditorParams:{values: this.customerList}},
    {headerName: '견적일자', field: 'estimateDate', editable:true, cellEditor: "datePicker"},
    {headerName: '수주여부', field: 'contractStatus'},
    {headerName: '견적요청자', field: 'estimateRequester', editable:true},
    {headerName: '유효일자', field: 'effectiveDate', editable:true, cellEditor: "datePicker"},
    {headerName: '견적담당자코드', field: 'personCodeInCharge', editable:true}
  ];
  components = { datePicker: getDatePicker() };
  frameworkComponents ={ agDateInput: GridDatePickerComponent };
  //견적상세 컬럼
  estimateDetailColumnDefs = [
    {headerName: '견적상세일련번호', field: 'estimateDetailNo',checkboxSelection: true},
    {headerName: '견적일련번호', field: 'estimateNo'},
    {headerName: '품목코드', field: 'itemCode',onCellClicked:params=>{this.onItemCellClick(params)}},
    {headerName: '품목명', field: 'itemName',onCellClicked:params=>{this.onItemCellClick(params)}},
    {headerName: '단위', field: 'unitOfEstimate',onCellClicked:params=>{this.onItemCellClick(params)}},
    {headerName: '납기일', field: 'dueDateOfEstimate', editable:true, cellEditor: "datePicker"},
    {headerName: '견적수량', field: 'estimateAmount', editable:true},
    {headerName: '견적단가', field: 'unitPriceOfEstimate', editable:true},
    {headerName: '합계액', field: 'sumPriceOfEstimate', editable:true,hide:true}
  ];

    //아이템다이얼로그 컬럼
    itemDialogColumnDefs = [
      {headerName: '품목코드', field: 'itemCode', onCellDoubleClicked:params2=>{this.itemCodeSetValue(params2);}},
      {headerName: '품목명', field: 'itemName', onCellDoubleClicked:params2=>{this.itemCodeSetValue(params2);}},
      {headerName: '단위', field: 'unitOfStock', onCellDoubleClicked:params2=>{this.itemCodeSetValue(params2);}},
      {headerName: '표준단가', field: 'standardUnitPrice', onCellDoubleClicked:params2=>{this.itemCodeSetValue(params2);}}
    ];


    itemCodeSetValue(params2){
      this.itemCode=params2.data.itemCode;
      this.itemName=params2.data.itemName;
      this.unitOfStock=params2.data.unitOfStock;
      this.unitPriceOfEstimate=params2.data.standardUnitPrice;

  //    alert(this.itemCode);
 //     alert(this.itemName);
 //     alert(this.unitOfStock);
 //     alert(this.unitPriceOfEstimate);
      }
dataState:dataState;
  readState() {
    this.dataState=this.store.getState() as dataState
   
    this.dataState.empCode
    if(this.dataState.empCode.trim().length>0){
      //alert(this.dataState.empCode)
      this.empCode=this.dataState.empCode;
    }
  }

}
function getDatePicker() {
 // alert("a");
  function Datepicker() {}
  Datepicker.prototype.init = function(params) {
    this.eInput = document.createElement("input");
    this.eInput.value = params.value;
    $(this.eInput).datepicker({ dateFormat: "yymmdd" });
  };
  Datepicker.prototype.getGui = function() {
    return this.eInput;
  };
  Datepicker.prototype.afterGuiAttached = function() {
    this.eInput.focus();
    this.eInput.select();
  };
  Datepicker.prototype.getValue = function() {
    return this.eInput.value;
  };
  Datepicker.prototype.destroy = function() {};
  Datepicker.prototype.isPopup = function() {
    return false;
  };
  return Datepicker;
}