import { Injectable } from '@angular/core';
import { HttpClient,  HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GatewayService {

  constructor(private http:HttpClient) {}

  httpParams:HttpParams;
/**
 * 직접 통신하게되는 메서드
 * @url {string} 
 * @inData {string}
 * @parameters {Map<string,string>}
 */
  send(strUrl:string,inData:string,parameters:Map<string,string>){
  var url="http://localhost:8282/57thERPangular";
  url=url+strUrl;
  this.httpParams=new HttpParams().set("inData",inData);
  if(parameters!=null){
  if(parameters.size>0){
    parameters.forEach ((val,key,map)=>{
      console.log(key);
      this.httpParams=this.httpParams.append(key,val);
    });
  }
}
  return this.http.post(url,this.httpParams);
  }
/**
 * Convert Object to HttpParams
 * @param {Object} obj
 * @returns {HttpParams}
 */
  toHttpParams(obj: Object): HttpParams {
  return Object.getOwnPropertyNames(obj)
      .reduce((p, key) => p.set(key, obj[key]), new HttpParams());
  }
}
  

