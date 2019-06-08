import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LogiService } from 'src/app/common/svc/logi.service';

@Component({
  selector: 'app-mrp-gathering-view',
  templateUrl: './mrp-gathering-view.component.html',
  styleUrls: ['./mrp-gathering-view.component.css']
})
export class MrpGatheringViewComponent implements OnInit {
  private logiService:LogiService;
  private http:HttpClient
  constructor(http:HttpClient) {
    this.http=http;
    this.logiService=new LogiService(this.http);
   }
  ngOnInit() {
    //this.findMrpG();
  }
public  mrpgList=[];
  findMrpG(){
    this.logiService.transaction("production/findMrpGatheringList.do","","gatheringStatus=N").subscribe((result : any[])=>{
    this.mrpgList=result["mrpGatheringList"];
    }); 

}
public mrpgColumnDefs = [
  {headerName: '소요량취합번호', field: 'mrpGatheringNo'},
  {headerName: '품목코드', field: 'itemCode'},
  {headerName: '제품명', field: 'itemName'},
  {headerName: '단위', field: 'unitOfMrpGathering'},
  {headerName: '필요수량', field: 'necessaryAmount'},
  {headerName: '지시일', field: 'claimDate'},
  {headerName: '납기일', field: 'dueDate'},
  {headerName: '생산지시/주문분류', field: 'orderOrProductionStatus',hide:true},
  {headerName: '공정상태', field: 'onGoingProcessStatus',hide:true},
  {headerName: 'status', field: 'status',hide:true}
];

}
