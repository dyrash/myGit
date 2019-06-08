import { Component, OnInit } from '@angular/core';
import { SysService } from 'src/app/common/svc/sys.service';

@Component({
  selector: 'app-menu-tree',
  templateUrl: './menu-tree.component.html',
  styleUrls: ['./menu-tree.component.css']
})
export class MenuTreeComponent implements OnInit {

  constructor(private sysService:SysService) { }

  appitems = []
  ngOnInit() {
    this.sysService.transaction("findSideMenuList.do","","").subscribe((result:any[])=>{
      this.appitems=result["menuList"];
    }); 
  }
  selectedItem(event){
    console.log(event);
  }

  config = {
    paddingAtStart: true,
    listBackgroundColor: '#fff',
    fontColor: 'rgb(8, 54, 71)',
    backgroundColor: '#fff',
    selectedListFontColor: 'red',
    interfaceWithRoute: true
  };
}
