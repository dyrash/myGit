import { Injectable, Inject } from '@angular/core';
import { dataStore } from '../redux/core/code.store';
import { Store } from 'redux';
import { dataState } from '../redux/core/code.state';

@Injectable({
  providedIn: 'root'
})
export class AuthCheckService {
  private authList:Array<any>;
  constructor( @Inject(dataStore) private store?: Store<dataState>) {
    store.subscribe(() => this.redux());
    this.redux();
   }

  checkAuth(strUrl:string) :boolean {
  
   var check=false; 
   if(!this.authList){
    console.log("누가 토큰으로 처리좀 해봐 .. 시간없어... ㅠㅠ");
    return false;
   }
    this.authList.forEach(element => {

      if(element["menuCode"]==strUrl){
        check=true;
      }
    });
 
    return check;

 
  }
  redux(){
    
    const state: dataState = this.store.getState() as dataState;
    this.authList=state.menuAuth["menuAuthorityList"];
  }
}
