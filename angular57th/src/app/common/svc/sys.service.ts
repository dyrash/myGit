import { Injectable } from '@angular/core';
import { GatewayService } from './gateway.service';
import { HttpClient  } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class SysService {
   private gatewayService:GatewayService;
  constructor(private http:HttpClient) {
  this.gatewayService=new GatewayService(http);
   }

  transaction(strUrl:string,indata:string,parameters:string){
    var pramMap= new Map<string,string>();
    var paramArray=new Array();
    var url="/sys/";

    url=url+strUrl;
    if(indata==undefined||parameters==undefined){
      
    }else if(parameters==""){
      return this.gatewayService.send(url,indata,null);
    }else{
     
      var params:string[];

      params=parameters.split(" ");
         params.forEach(element => {
        var key:string;
        key=element.substring(0,element.search("="));
        var val=element.substring(element.search("=")+1,element.length);
       pramMap.set(key,val);
     console.log(pramMap);
    });
     console.log(JSON.stringify(paramArray));
     console.log(JSON.parse(JSON.stringify(pramMap)));
     return this.gatewayService.send(url,indata,pramMap);
    };
      
  }
}
