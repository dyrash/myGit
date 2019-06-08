import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private url="http://localhost:8282/57thERPangular/";


  constructor(private http:HttpClient) { 

  }

  register(test : any){
    let headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;'});
  let options = { headers: headers };

    return this.http.post(this.url+"sys/checkLogin.do?checkInfo="+encodeURI(JSON.stringify(test)),test,options)
    console.log("==================\n"+ test);

  }

}
