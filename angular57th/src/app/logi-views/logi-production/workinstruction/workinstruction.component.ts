import { Component, OnInit, ViewChild, Inject } from '@angular/core';

import { AgGridNg2 } from 'ag-grid-angular';
import { HttpClient } from '@angular/common/http';

import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
import { SysService } from 'src/app/common/svc/sys.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { LogiService } from 'src/app/common/svc/logi.service';

@Component({
  selector: 'app-workinstruction',
  templateUrl: './workinstruction.component.html',
})

export class WorkinstructionComponent {
  @ViewChild('mrpGatheringGrid') mrpGatheringGrid: AgGridNg2;
  @ViewChild('workinstructionGrid') workinstructionGrid: AgGridNg2;
  @ViewChild('materialCheckGrid') materialCheckGrid: AgGridNg2;
  @ViewChild('content') content:NgbModal;
  

  title = 'workinstructionForm';
  logiService:LogiService;
  sysService:SysService
  counter: number;

  itemCode: string = '';
  itemName: string = ''; 
  stocktaking: string = '';
  safetyStocktaking: string = '';
  stockStatus: string = '';
  safetyStockStatus: string = '';

  constructor(public http:HttpClient, calendar: NgbCalendar, private modalService: NgbModal) {

    this.logiService=new LogiService(http);
 
   }
   
   private mrpGatheringList = [];
   ngOnInit() {
     this.Http();
   }
   
  Http(){
    this.logiService.transaction("production/findMrpGatheringList.do","","gatheringStatus=N").subscribe((result : any[])=>{
     
    this.mrpGatheringList = result['mrpGatheringList'];
  
    console.log(  this.mrpGatheringList);
    });
      
  }
  workInstruction=[]
  materialCheckTemp=[];
  closeResult: string;
  public mrpGatheringNum;
  onMaterialCellClick(params){
    this.mrpGatheringNum=params.data.mrpGatheringNo
    alert(this.mrpGatheringNum);
    this.logiService.transaction("production/findMaterialCheckTempList.do","","mrpGno="+this.mrpGatheringNum)
    .subscribe((result:any[])=>{
      console.log("dddddd");
      console.log(result['materialCheckList']);
      this.logiService.transaction("production/findWorkInstructionList.do","","mrpGno="+this.mrpGatheringNum).subscribe((result:any[])=>{
        this.workInstruction=result['workInstructionList'];
      });
      this.materialCheckTemp=result["materialCheckList"];
      this.Http();
     // console.log(result);
    });
    this.modalService.open(this.content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      //this.workinstructionGrid.api.redrawRows();
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


   mrpGatheringColumnDefs = [
    {headerName: '소요량취합번호', field: 'mrpGatheringNo',onCellClicked:params=>{this.onMaterialCellClick(params)}},
    {headerName: '구매및생산여부', field: 'orderOrProductionStatus'},
    {headerName: '품목코드', field: 'itemCode'},
    {headerName: '품목명', field: 'itemName'},
    {headerName: '단위', field: 'unitOfMrpGathering'},
    {headerName: '필요수량', field: 'necessaryAmount'},
    {headerName: '납기일', field: 'dueDate'},
    {headerName: '지시일', field: 'claimDate'},
    {headerName: '공정진행여부', field: 'onGoingProcessStatus'}
  ];

  
  workInstructionColumnDefs = [
    {headerName: '작업지시번호', field: 'workInstructionNo'},
    {headerName: '소요량취합번호', field: 'mrpGatheringNo'},
    {headerName: '품목코드', field: 'itemCode'},
    {headerName: '품목명', field: 'itemName'},
    {headerName: '지시일자', field: 'instructionDate'},
    {headerName: '단위', field: 'unitOfWorkInstruction'},
    {headerName: '작업지시수량', field: 'workInstructionAmount'},
    {headerName: '생산완료상태', field: 'productionStatus'},
    {headerName: '설명', field: 'description',hide:true},
    {headerName: '합계액', field: 'workInstructionStatus'}

  ];

  materialCheckGridColumnDefs =[
    {headerName: '제품코드', field: 'itemCode', onCellDoubleClicked:params2=>{this.materialSetValue(params2);}},
    {headerName: '제품명', field: 'itemName', onCellDoubleClicked:params2=>{this.materialSetValue(params2);}},
    {headerName: '재고량', field: 'stocktaking'},
    {headerName: '안전재고량', field: 'safetyStocktaking', onCellDoubleClicked:params2=>{this.materialSetValue(params2);}},
    {headerName: '재고상태', field: 'stockStatus', onCellDoubleClicked:params2=>{this.materialSetValue(params2);}},
    {headerName: '안전재고여부', field: 'safetyStockStatus', onCellDoubleClicked:params2=>{this.materialSetValue(params2);}},
  ];

  materialSetValue(params2){
      this.itemCode=params2.data.itemCode;
      this.itemName=params2.data.itemName;
      this.stocktaking=params2.data.stocktaking;
      this.safetyStocktaking=params2.data.safetyStocktaking;
      this.stockStatus=params2.data.stockStatus;
      this.safetyStockStatus=params2.data.safetyStockStatus;
  }
  
}