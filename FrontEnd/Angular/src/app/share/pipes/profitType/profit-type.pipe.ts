import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'profitType'
})
export class ProfitTypePipe implements PipeTransform {

  transform(profit:string): string {
    switch(profit){
      case "JCP" : return "Juros sobre capital próprio";
      case "DY" : return "Dividendos"
    }
    return "";
  }

}
