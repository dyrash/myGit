import { Pipe, PipeTransform } from '@angular/core';
import { MenuList } from '../shared/menuList.model'

@Pipe({
  name: 'menuPipe'
})
export class MenuPipePipe implements PipeTransform {

  transform(items: any[], filter: MenuList): any {
    return items.filter(item => item.menuName.indexOf(filter.level) !== -1); 
  }

}
