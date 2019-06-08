import { Injectable, Inject } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, UrlSegment } from '@angular/router';
import { AuthCheckService } from '../svc/auth-check.service';
import { dataStore } from '../redux/core/code.store';
import { Store } from 'redux';
import { dataState } from '../redux/core/code.state';

@Injectable({
  providedIn: 'root'
})
export class RoutingGuard implements CanActivate {
  acs:AuthCheckService;
  constructor(private router:Router, @Inject(dataStore) private store: Store<dataState>){
   
    this.acs=new AuthCheckService(store);
  }
  canActivate(next: ActivatedRouteSnapshot,state: RouterStateSnapshot)
  : boolean {
    //alert(next);
    var check=true;
    var url=next.url.map(UrlSegment=>UrlSegment.path)
    url.forEach(value=>{
      console.log(value);
      if(this.acs.checkAuth(value)){
     
        check=false;
      }
    })
    //check=false;
    if(check){
      alert("권한이없는접근입니다");
    this.router.navigate(['login-form']);
    return false;
  }
    return true;
  }
  
}
