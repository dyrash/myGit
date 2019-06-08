import { Component, OnInit, ViewChild } from '@angular/core';
import { AgGridNg2 } from 'ag-grid-angular';

import { LogiService } from 'src/app/common/svc/logi.service';
import { HttpClient } from '@angular/common/http';
import { MrpGatheringViewComponent } from './mrp-gathering-view/mrp-gathering-view.component';

@Component({
  selector: 'app-mrp-view',
  templateUrl: './mrp-view.component.html',
  styleUrls: ['./mrp-view.component.css']
})

export class MrpViewComponent implements OnInit {
  @ViewChild('mpsGrid') mpsGrid: AgGridNg2;
  @ViewChild('mrpGrid') mrpGrid: AgGridNg2;
  @ViewChild(MrpGatheringViewComponent) mrpgComponent: MrpGatheringViewComponent;//자식컴포넌트의 주소값획득법
  private logiService:LogiService;
  private http:HttpClient;
  constructor(http:HttpClient) {
    this.http=http;
    this.logiService=new LogiService(this.http);

   }
mpsList=[];
mrpList=[];
  ngOnInit() {
    this.findMps();
    this.findMrp();
  }
  findMps(){
    this.logiService.transaction("production/findMpsList.do","","fromDate=mrpSelect toDate=0").subscribe((result : any[])=>{
    this.mpsList=result["mpsList"];
    });
  }
  mpsColumnDefs = [
    {headerName: '생산계획번호', field: 'mpsNo', checkboxSelection: true },
    {headerName: '구분', field: 'mpsPlanClassification',hide:true},
    {headerName: '품목코드', field: 'itemCode'},
    {headerName: '제품명', field: 'itemName'},
    {headerName: '수주상세번호', field: 'contractDetailNo'},
    {headerName: 'salesPlanNo', field: 'salesPlanNo',hide:true},
    {headerName: '단위', field: 'unitOfMps'},
    {headerName: '계획일자', field: 'mpsPlanDate'},
    {headerName: '계획수량', field: 'mpsPlanAmount'},
    {headerName: '납기일', field: 'dueDateOfMps'},
    {headerName: '예정마감일', field: 'scheduledEndDate'},
    {headerName: '소요량전개상태', field: 'mrpApplyStatus'},
    {headerName: '비고', field: 'description',hide:true},
    {headerName: 'status', field: 'status',hide:true}
  ];
  mrpColumnDefs = [
    {headerName: '생산계획번호', field: 'mpsNo'},
    {headerName: '소요량전개번호', field: 'mrpNo'},
    {headerName: '취합상태', field: 'mrpGatheringStatus'},
    {headerName: '소요량취합번호', field: 'mrpGatheringNo'},
    {headerName: '품목코드', field: 'itemCode'},
    {headerName: '제품명', field: 'itemName'},
    {headerName: '분류', field: 'itemClassifcation'},
    {headerName: '단위', field: 'unitOfMrp'},
    {headerName: '필요수량', field: 'requiredAmount'},
    {headerName: '발주일자', field: 'orderDate'},
    {headerName: '소요일자', field: 'requiredDate'},
    {headerName: 'status', field: 'status',hide:true}
  ];
  registMrp(){
    const selectedNodes = this.mpsGrid.api.getSelectedNodes();
    console.log(selectedNodes);
    const selectedData = selectedNodes.map( node => node.data);
    console.log(JSON.stringify(selectedData));

    this.logiService.transaction("production/findMrpOpenTempList.do",JSON.stringify(selectedData),"").subscribe((result : any[])=>{
    //this.mrpList=result["mrpOpenTempList"];
    this.findMps();
    this.findMrp();
    });
  }
  findMrp(){
    this.logiService.transaction("production/findMrpList.do","","mrpgStatus=NO").subscribe((result : any[])=>{
    this.mrpList=result["mrpList"];
    }); 

  }
  registMrpG(){
    var batchData=[]; 
    this.mrpGrid.rowData.forEach(element => {
       //console.log(element);
       if(element.mrpGatheringStatus!="Y"){
        // console.log(element);
         batchData.push(element);
       }
     });
     console.log(batchData);
    this.logiService.transaction("production/registMrpGathering.do",JSON.stringify(batchData),"").subscribe((result : any[])=>{
      this.findMrp();
      this.mrpgComponent.findMrpG();
      }); 

  }
}
