import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { GatewayService } from 'src/app/common/svc/gateway.service';
@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
   private gatewayService:GatewayService;

  constructor(private http:HttpClient) {
  this.gatewayService=new GatewayService(http);
   }

  transaction(strUrl:string,indata:string,parameters:string){
    var pramMap= new Map<string,string>();
    var paramArray=new Array();
    var url="/";

    url=url+strUrl;
    if(indata==undefined||parameters==undefined){
      
    }else if(parameters==""){
      return this.gatewayService.send(url,indata,null);
    }else{
     
      var params:string[];

      params=parameters.split(" ");
      //alert(params.length);
       params.forEach(element => {
        var key:string;
        key=element.substring(0,element.search("="));
        var val=element.substring(element.search("=")+1,element.length);
      //alert(key);
      //alert(val);
      paramArray.push(key);
      paramArray.push(val); 
      pramMap.set(key,val);
    });
     console.log(JSON.stringify(paramArray));
     return this.gatewayService.send(url,indata,pramMap);
    };
  }

}
