import { Component, OnInit, ViewChild  } from '@angular/core';

import { AgGridNg2 } from 'ag-grid-angular';
import { HttpClient } from '@angular/common/http';
import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
import { SysService } from 'src/app/common/svc/sys.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { LogiService } from 'src/app/common/svc/logi.service';

import {
  trigger,
  state,
  style,
  animate,
  transition
} from '@angular/animations';

interface data{
  contractDetailNo: string;
  contractNo: string;
  itemCode: string;
  itemName: string;
  unitOfContract: string;
  dueDateOfContract: string;
  contractAmount: string;
  unitPriceOfContract: string;
  sumPriceOfContract: string;
  mpsApplyStatus: string;
  deliveryStatus: string;
  status: string;
}

@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
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
    trigger('toggle', [
      state('show', style({
        opacity: 1,height:300
      })),
      state('hide',   style({
        height:10,  opacity: 0,
      })),
      transition('show => hide', animate('600ms ease-out')),
      transition('hide => show', animate('1000ms ease-in'))
    ])
  ]
})

export class ContractComponent implements OnInit {
  @ViewChild('contractGrid') contractGrid: AgGridNg2;
  @ViewChild('contractDetailGrid') contractDetailGrid: AgGridNg2;
  @ViewChild('content') content:NgbModal;

  hoveredDate: NgbDate;

  fromDate: NgbDate;
  toDate: NgbDate;

  title = 'ContractForm';
  logiService:LogiService;
  sysService:SysService
  counter: number;

  estimateNo: string = '';
  customerCode: string = '';
  estimateDate: string = '';
  effectiveDate: string = '';
  estimateRequester: string = '';
  personCodeInCharge: string = '';

  public contractDetailList = [];
  private dataArray = [];
  private contractArray=[];//contract

  constructor(public http:HttpClient, private modalService: NgbModal, calendar: NgbCalendar) {
    this.fromDate = calendar.getToday();
    this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);

    this.logiService=new LogiService(http);
    this.sysService=new SysService(http);
   }
   get stateName() {
    return this.datePickerCheck ? 'show' : 'hide'
  }
   datePickerCheck=false;
  //달력 function
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
  
  public contractNum: string;
  ngOnInit() {

  }
  ngAfterViewInit(){
    this.contractGrid.api.sizeColumnsToFit();  
    this.contractDetailGrid.api.sizeColumnsToFit();  
  } 
   //날자 function
   getFormatDate(monthday){
    monthday = monthday >= 10 ? monthday : '0' + monthday;
    return  monthday;
  }

   //수주조회
   public fromDays: string;
   public toDays: string;
   findContractList() {
     var fromYear=String(this.fromDate.year);
     var fromMonth=this.getFormatDate(String(this.fromDate.month));
     var fromDay=this.getFormatDate(String(this.fromDate.day));
     
     this.fromDays=fromYear+fromMonth+fromDay;
     
     var toYear=String(this.toDate.year);
     var toMonth=this.getFormatDate(String(this.toDate.month));
     var toDay=this.getFormatDate(String(this.toDate.day));
 
     this.toDays=toYear+toMonth+toDay;
 
     const selectedNodes = this.contractGrid.api.getSelectedNodes();
     console.log(selectedNodes);
     const selectedData = selectedNodes.map( node => node.data);
     console.log(JSON.stringify(selectedData));
 
     this.Http();
 }

  Http(){
    this.logiService.transaction("business/findContractList.do","","fromDate="+this.fromDays+" toDate="+this.toDays+"").subscribe((result : any[])=>{
    this.contractArray = result;
    console.log(this.contractArray);
    });
      
  }

//수주추가

addContract(){
  this.sysService.transaction("findSequenceNo.do","","findSeq=SERIAL_SEQ").subscribe((result : any[])=>{
    var sqno=result;
    this.contractNum="CONT"+sqno["squenceNo"];

    var contractList=[];
    var newData={
      contractNo:this.contractNum, estimateNo:""
      , contractType:"CT-01", customerCode:""
      , contractDate:"", effectiveDate:""
      , personCodeInCharge:""
      ,contractDetailList:[], status: "inserted"};
    contractList.push(newData);
    if(this.contractArray["contractList"]==undefined){
      this.contractArray["contractList"]=contractList;
   
    }else{
      this.contractArray["contractList"].push(newData);
      this.contractGrid.api.setRowData(this.contractArray["contractList"]);
    }
  
   this.contractGrid.api.redrawRows();
   console.log(this.contractArray["contractList"]);
  });
  }

  //수주 다이얼로그
  
 
  closeResult: string;
  onEdialogCellClick(params){
    var num = 1;
    var contractDetailNum: string;
    console.log(params);
    this.logiService.transaction("business/findEstimateDialog.do","","").subscribe((result : any[])=>{
      this.dataArray = result;
      console.log(this.dataArray);
      });
    this.modalService.open(this.content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      params.data.estimateNo=this.estimateNo;
      params.data.customerCode=this.customerCode;
      params.data.contractDate=this.estimateDate;
      params.data.effectiveDate=this.effectiveDate;
      params.data.contractRequester=this.estimateRequester;
      params.data.personCodeInCharge=this.personCodeInCharge;

      //var index=0;
      this.contractDetailList.forEach((element,index) => {
          var contDetail={}; 
          console.log(element);

          contractDetailNum=this.contractNum+"-0"+num++;
 
          contDetail["contractDetailNo"]=contractDetailNum;
          contDetail["contractNo"]=params.data.contractNo;
          contDetail["itemCode"]=element.itemCode;
          contDetail["itemName"]=element.itemName;
          contDetail["unitOfContract"]=element.unitOfEstimate;
          contDetail["dueDateOfContract"]=element.dueDateOfEstimate;
          contDetail["contractAmount"]=element.estimateAmount;
          contDetail["unitPriceOfContract"]=element.unitPriceOfEstimate
          contDetail["sumPriceOfContract"]=element.sumPriceOfEstimate;
          contDetail["mpsApplyStatus"]="N";
          contDetail["deliveryStatus"]="N";
          contDetail["status"]="inserted";

          params.data.contractDetailList[index]=contDetail;
         
        index++;
      });
      console.log(params.data);
      this.contractDetailList=params.data.contractDetailList;
 
      console.log(this.contractDetailList);

      this.contractDetailGrid.api.redrawRows();
      this.contractGrid.api.redrawRows();
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

   //수주 삭제
   deleteContract(){
    this.contractGrid.api.getSelectedNodes().forEach(element => {
      this.dataArray["contractList"][element.rowIndex].status="deleted"
    });
    this.contractGrid.api.redrawRows();
  }

   //저장
   private contractDetailArray={};
   saveContract(){
  
  var batchData=[];
  this.contractGrid.api.forEachNode((node,index)=> {
 
    if(node.data.status=="inserted"){

      batchData.push(node.data);
     }
   });
   console.log(batchData);
   this.logiService.transaction("business/registerContract.do",JSON.stringify(batchData),"").subscribe(()=>{
    this.findContractList(); 
    this.contractDetailList=[];
    
    this.dataArray = [];
    this.contractArray=[];

    alert("저장성공");
 
    this.contractGrid.api.redrawRows();
    this.contractDetailGrid.api.redrawRows();
    this.findContractList();
   });
    }

  //수주상세 조회
  onCellClick(params:any){
  var contractnum =  params.data;
  this.contractDetailList = contractnum["contractDetailList"];
  this.logiService.transaction("business/findContractDetailList.do","","contractNo="+contractnum+"").subscribe();
  this.contractDetailGrid.api.redrawRows();
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

  //수주 컬럼
  contractColumnDefs = [
    {headerName: '수주일련번호', field: 'contractNo',checkboxSelection: true,onCellClicked:params=>{this.onCellClick(params)}},
    {headerName: '견적일련번호', field: 'estimateNo',onCellClicked:params=>{this.onEdialogCellClick(params)}},
    {headerName: '수주유형분류', field: 'contractType'},
    {headerName: '거래처코드', field: 'customerCode'},
    {headerName: '수주일자', field: 'contractDate'},
    {headerName: '수주요청자', field: 'contractRequester'},
    {headerName: '수주담당자코드', field: 'personCodeInCharge'},
    {headerName: '배달상태', field: 'deliveryResultStatus',hide:true},
    {headerName: '전표등록상태', field: 'slipRegistStatus',hide:true}
  ];

    //수주상세 컬럼
    contractDetailColumnDefs = [
      {headerName: '수주상세일련번호', field: 'contractDetailNo',checkboxSelection: true},
      {headerName: '수주일련번호', field: 'contractNo'},
      {headerName: '품목코드', field: 'itemCode'},
      {headerName: '품목명', field: 'itemName'},
      {headerName: '단위', field: 'unitOfContract'},
      {headerName: '납기일', field: 'dueDateOfContract'},
      {headerName: '수주수량', field: 'contractAmount'},
      {headerName: '수주단가', field: 'unitPriceOfContract'},
      {headerName: '합계액', field: 'sumPriceOfContract'},
      {headerName: 'MPS적용상태', field: 'mpsApplyStatus'},
      {headerName: '납품여부', field: 'deliveryStatus'}
 
    ];

    //견적 다이얼로그 컬럼
    estimateDialogColumnDefs = [
      {headerName: '견적일련번호', field: 'estimateNo', onCellDoubleClicked:params2=>{this.estimateSetValue(params2);}},
      {headerName: '거래처명', field: 'customerCode', onCellDoubleClicked:params2=>{this.estimateSetValue(params2);}},
      {headerName: '견적일자', field: 'estimateDate', onCellDoubleClicked:params2=>{this.estimateSetValue(params2);}},
      {headerName: '견적유효일자', field: 'effectiveDate', onCellDoubleClicked:params2=>{this.estimateSetValue(params2);}},
      {headerName: '견적요청자', field: 'estimateRequester', onCellDoubleClicked:params2=>{this.estimateSetValue(params2);}},
      {headerName: '담당자', field: 'personCodeInCharge', onCellDoubleClicked:params2=>{this.estimateSetValue(params2);}}
    ];

    estimateSetValue(params2){
      console.log(params2);
      this.estimateNo=params2.data.estimateNo;
      this.customerCode=params2.data.customerCode;
      this.estimateDate=params2.data.estimateDate;
      this.effectiveDate=params2.data.effectiveDate;
      this.estimateRequester=params2.data.estimateRequester;
      this.personCodeInCharge=params2.data.personCodeInCharge;
      this.contractDetailList=params2.data.estimateDetailList;
      console.log(this.contractDetailList);

      }


}