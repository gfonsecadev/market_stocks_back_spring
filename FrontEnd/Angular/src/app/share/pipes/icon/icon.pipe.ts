import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'icon'
})
export class IconPipe implements PipeTransform {

  transform(value: string): string{
    switch(value){
      case "geral" : return "emergency";
      case "finance" : return "paid";
      case "tech" : return "smart_toy";
      case "constr" : return "apartment";
      case "agrib" : return "agriculture";
      case "foodDrink" : return "fast-food";
      case "educ" : return "school";
      case "eletr" : return "cable";
      case "mall" : return "local-mall";
      case "miniSteel" : return "landslide";
    }
    return 'code'
  }

}
