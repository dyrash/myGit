import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // var token = localStorage.getItem('token'); //1
    // var newHeader: HttpHeaders = req.headers; //2
    // newHeader = newHeader.set('Content-Type', 'application/json'); //3
    // if(token) newHeader = newHeader.set('x-access-token', token); //4
    // const newReq = req.clone({headers: newHeader}); //5
 
    console.log(req.body);
    return next.handle(req); //6
  }
}