import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SysService } from 'src/app/common/svc/sys.service';


@Component({
  selector: 'app-top',
  templateUrl: './top.component.html',
  styleUrls: ['./top.component.css']
})
export class TopComponent implements OnInit {
  private url="http://localhost:8282/57thERP/";
  private dataArray = [];
  sysService:SysService;
  filterLev = {level : "1"};
  constructor(public http : HttpClient) { 
    this.sysService=new SysService(http);
    // let result = this.filter.transform(this.dataArray, {level : "1"});
    // console.log(result);

  }

  ngOnInit() {
    this.findMenuList();
  }

  findMenuList(){
      this.sysService.transaction("findMenuList.do","","").subscribe((result : any[])=>{
      this.dataArray = result;
      console.log(this.dataArray);
      });
    // this.http.get(this.url + "sys/findMenuList.do").subscribe((result : any[])=>{
    //   this.dataArray = result;
    //   console.log(this.dataArray);
    //});
  }
}
