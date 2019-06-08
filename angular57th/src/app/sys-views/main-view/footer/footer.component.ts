import { Component, OnInit, Inject } from '@angular/core';
import * as InsertActions from 'src/app/common/redux/core/codedata.action';
import { dataState } from 'src/app/common/redux/core/code.state';
import { Store } from 'redux';
import { dataStore } from 'src/app/common/redux/core/code.store';
import { HttpClient } from '@angular/common/http';
import { SysService } from 'src/app/common/svc/sys.service';
import { LogiService } from 'src/app/common/svc/logi.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  sysService:SysService;
  logiService:LogiService;
  constructor(public http:HttpClient,@Inject(dataStore) public store: Store<dataState>) { 
    store.subscribe(() => this.readState());
    this.sysService=new SysService(http);
    this.logiService=new LogiService(http);
  }

  
  ngOnInit() {
    this.Http();
  }
private reduxArray:Array<any>=new Array;
  Http(){
    this.sysService.transaction("findCodeList.do","","a=HR01 b=HR02").subscribe((result : any[])=>{
  //  alert(JSON.stringify(result));
      var dataArray = result;
    this.reduxArray.push(result);
 //   console.log(this.dataArray);
    this.redux(dataArray);  
    this.sysService.transaction("findCustomerList.do","","a=HR01 b=HR02").subscribe((result : any[])=>{
   //   alert(JSON.stringify(result));
  
      var dataArray = result;
      this.reduxArray.push(dataArray);
   //   console.log(this.dataArray);
      this.redux(dataArray);  
      this.sysService.transaction("findCodeDetailList.do","","a=HR01 b=HR02").subscribe((result : any[])=>{
     //   alert(JSON.stringify(result));
  
        var dataArray = result;
        this.reduxArray.push(dataArray);
        this.sysService.transaction("findCompanyList.do","","a=HR01 b=HR02").subscribe((result : any[])=>{
       //   alert(JSON.stringify(result));
  
          var dataArray= result;
          this.reduxArray.push(dataArray);
       //   console.log(this.dataArray);
          this.redux(dataArray);  
          this.sysService.transaction("findBaseYearList.do","","businessPlaceCode=BRC-01").subscribe((result : any[])=>{
         //   alert(JSON.stringify(result));
  
            var dataArray = result;
            this.reduxArray.push(dataArray);
         //   console.log(this.dataArray);
            this.redux(dataArray);  
            this.logiService.transaction("purchase/findStockList.do","","").subscribe((result : any[])=>{
           //   alert(JSON.stringify(result));
  
              var dataArray = result;
              this.reduxArray.push(dataArray);
              //   console.log(this.dataArray);
                 this.redux(dataArray);  
              });
          });  
        });  
        //  console.log(this.dataArray);
        //this.redux(dataArray);  
        });

    });

  });
            this.sysService.transaction("findMenuAuthorityList.do","","authorityCode=SYSTEM").subscribe((result : any[])=>{
              var dataArray = result;
           //   console.log(this.dataArray);
              this.menuRedux(dataArray);  
              });                    
  }
  readState() {
   
  }
 redux(dataArray){
  console.log(dataArray);
  this.store.dispatch(InsertActions.insert(dataArray));

  }
  menuRedux(dataArray){
    console.log(dataArray);
    this.store.dispatch(InsertActions.insertAuth(dataArray));
  
    }
}
