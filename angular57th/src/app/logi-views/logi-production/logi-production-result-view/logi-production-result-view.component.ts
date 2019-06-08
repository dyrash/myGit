import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LogiService } from 'src/app/common/svc/logi.service';
import { AgGridNg2 } from 'ag-grid-angular';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import {
  trigger,
  state,
  style,
  animate,
  transition
} from '@angular/animations';

const datePipe = new DatePipe("en-US");
const toDay = datePipe.transform(new Date(), 'yyyyMMdd');
@Component({
  selector: 'app-logi-production-result-view',
  templateUrl: './logi-production-result-view.component.html',
  styleUrls: ['./logi-production-result-view.component.css'],
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

export class LogiProductionResultViewComponent implements OnInit {
  @ViewChild('workInstruction_grid') agGrid: AgGridNg2;
  @ViewChild('prm_grid') agGrid2: AgGridNg2;
  hoveredDate: NgbDate;
  fromDate: NgbDate;
  toDate: NgbDate;

  logiService:LogiService;

  constructor(http:HttpClient) {
  this.logiService=new LogiService(http);
   }
private workInstructionArray=[];
private prmArray=[];
private fromDays: string;
private toDays: string;

workInstructionColumnDefs = [
  {headerName: '작업지시번호', field: 'workInstructionNo',checkboxSelection: true },
  {headerName: '취합번호', field: 'mrpGatheringNo'},
  {headerName: '품목코드', field: 'itemCode'},
  {headerName: '제품명', field: 'itemName'},
  {headerName: '단위', field: 'unitOfWorkInstruction'},
  {headerName: '수량', field: 'workInstructionAmount'},
  {headerName: '생산상태', field: 'productionStatus',hide:true},
  {headerName: '비고', field: 'description',hide:true},
  {headerName: '작업지시여부', field: 'workInstructionStatus',hide:true},
  {headerName: '작업지시일', field: 'instructionDate'},
  {headerName: 'status', field: 'status',hide:true}
];
prmColumnDefs=[
  {headerName: '생산번호', field: 'productionResultNo'},
  {headerName: '작업지시번호', field: 'workInstructionNo'},
  {headerName: '품목코드', field: 'itemCode'},
  {headerName: '제품명', field: 'itemName'},
  {headerName: '생산일', field: 'productionDate'},
  {headerName: '생산수량', field: 'productionAmount'},
  {headerName: '단위', field: 'unitOfProductionResult'},
  {headerName: '비고', field: 'description',hide:true},
  {headerName: 'status', field: 'status',hide:true}
];

  ngOnInit() {
    this.findWorkInstruction();
   
  }
  findWorkInstruction(){
    this.logiService.transaction("production/findWorkInstructionList2.do","","").subscribe((result : any[])=>{
      this.workInstructionArray = result;
      console.log(this.workInstructionArray);
      this.agGrid.api.redrawRows();
      });
  }
  registPrm(){

    const selectedNodes = this.agGrid.api.getSelectedNodes();
    console.log(selectedNodes);
    const selectedData = selectedNodes.map( node => node.data);
    console.log(JSON.stringify(selectedData[0]["workInstructionNo"]));
    this.logiService.transaction("production/registPrm.do",JSON.stringify(selectedData),"deptCode=dpt01 empCode=1111 reportingDate=20190326 businessCode=brc-01").subscribe(()=>{
  
    this.findWorkInstruction();
    this.getTodayPrm();
    });
  }
  getTodayPrm(){

    this.logiService.transaction("production/findPrmList.do","","fromDate="+toDay+" toDate="+toDay).subscribe((result : any[])=>{
    this.prmArray = result;
    console.log(this.prmArray);
    this.agGrid2.api.redrawRows();
    });
  }

  
  findPrm(){
    var fromYear=String(this.fromDate.year);
    var fromMonth=this.getFormatDate(String(this.fromDate.month));
    var fromDay=this.getFormatDate(String(this.fromDate.day));
    
    this.fromDays=fromYear+fromMonth+fromDay;
    
    var toYear=String(this.toDate.year);
    var toMonth=this.getFormatDate(String(this.toDate.month));
    var toDay=this.getFormatDate(String(this.toDate.day));

    this.toDays=toYear+toMonth+toDay;

    this.logiService.transaction("production/findPrmList.do","","fromDate="+this.fromDays+" toDate="+this.toDays).subscribe((result : any[])=>{
      this.prmArray = result;
      console.log(this.prmArray);
      this.agGrid2.api.redrawRows();
      });
  }


   datePickerCheck=false;

  get stateName() {
    return this.datePickerCheck ? 'show' : 'hide'
  }
  dateSlide_OnClick(event){
    this.datePickerCheck=event.checked;
     console.log(event.checked);
  }
  
  getFormatDate(monthday){
    monthday = monthday >= 10 ? monthday : '0' + monthday;
    return  monthday;
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
    console.log(this.fromDate);
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {

    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  
  }
}