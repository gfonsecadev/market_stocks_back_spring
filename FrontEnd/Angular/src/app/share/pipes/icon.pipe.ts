import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'icon'
})
export class IconPipe implements PipeTransform {

  transform(value: string): string{
    switch(value){
      case 'tecnologia': return "developer_board";
      case "construção": return "apartment"
    }
    return 'code'
  }

}
