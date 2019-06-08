import { Component, OnInit, ViewChild, ElementRef, Inject } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import * as InsertActions from 'src/app/common/redux/core/codedata.action';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { SysService } from 'src/app/common/svc/sys.service';
import { dataStore } from 'src/app/common/redux/core/code.store';
import { Store } from 'redux';
import { dataState } from 'src/app/common/redux/core/code.state';


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  @ViewChild('closeAddExpenseModal') closeAddExpenseModal: ElementRef;
  @ViewChild('closeAddExpenseModal2') closeAddExpenseModal2: ElementRef;

  loginForm : FormGroup;
  empNo;
  ngPlaceCode :string = '';
  ngDeptCode :string = '';
  display='none';

  constructor(public http : HttpClient, 
              private fb : FormBuilder,
              private loginService : LoginService,
              private router : Router,
              private sysService:SysService,
              @Inject(dataStore) public store: Store<dataState>
              ) {
    this.loginForm = this.fb.group({
      placeCode: ['', Validators.required],
      deptCode: ['', Validators.required],
      employeeNo: ['', Validators.required],
      password: ['', Validators.required]
  });
  store.subscribe(() => this.readState());
   }
  private url="http://localhost:8282/57thERPangular/";
  private dataArray = [];
  ngOnInit() {
   this.resetForm();
   //this.findPlaceCode();
  }
  resetForm(form? : NgForm){
    //form.reset();
      } 
readState(){}
  findPlaceCode(){
    this.http.get(this.url + "sys/findBusinessPlaceList.do").subscribe((result : any[])=>{
      this.dataArray = result;
      console.log(this.dataArray);
    });
  }
  businessColumnDefs = [
    {headerName: 'businessPlaceCode', field: 'businessPlaceCode', 
    width: 200, onCellDoubleClicked:params=>{this.businessPlaceCodeSetValue(params);}},
    {headerName: 'businessPlaceName', field: 'businessPlaceName', 
    width: 200, onCellDoubleClicked:params=>{this.businessPlaceCodeSetValue(params);}}
  ];

  findDeptCode(){
    console.log(":::"+this.loginForm.get('placeCode').value+":::");
    if(this.loginForm.get('placeCode').value===""){
      alert("회사코드 선택");
      return;
    }
   
    this.http.get(this.url + "sys/findAllDeptList.do")
    .subscribe((result : any[])=>{
      this.dataArray = result;
      console.log(this.dataArray);
    });
  
  }
    deptColumnDefs = [
      {headerName: 'deptCode', field: 'deptCode', 
      width: 200, onCellDoubleClicked:params2=>{this.deptCodeSetValue(params2);}},
      {headerName: 'deptName', field: 'deptName', 
      width: 200, onCellDoubleClicked:params2=>{this.deptCodeSetValue(params2);}}
    ];
  login(){
    alert("aaaaa")
  }
  showModalFunc(){
    
  }
  businessPlaceCodeSetValue(params){
   // alert(params.data.businessPlaceCode);
    this.loginForm.get('placeCode').setValue(params.data.businessPlaceCode);
    this.closeAddExpenseModal.nativeElement.click();
    
  }
  deptCodeSetValue(params){
  //  alert(params.data.deptCode);
    this.loginForm.get('deptCode').setValue(params.data.deptCode);
    this.closeAddExpenseModal2.nativeElement.click();
  }

//로그인
  onLogin(){
    if (this.loginForm.invalid) {
      alert("모든항목을 입력하세요");
    //  alert(this.loginForm.status);
      return;
  }
    // alert(JSON.stringify(this.loginForm.value));
     let test = this.loginForm.value;
     this.sysService.transaction("checkLogin.do",JSON.stringify(test),"")
     //this.loginService.register(test)
     .subscribe(
       data => {
       //  alert(this.empNo);
         alert('SUCCESS!! :-)\n\n');
         this.store.dispatch(InsertActions.insertUser(this.empNo))
         this.router.navigate(['/main/welcome']);
         console.log(JSON.stringify(this.loginForm.value));
     }
     );
  }

  
}