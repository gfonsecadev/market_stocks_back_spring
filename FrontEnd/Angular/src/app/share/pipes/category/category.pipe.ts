import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'category'
})
export class CategoryPipe implements PipeTransform {

  transform(category:string): string{
    switch(category){
      case "geral" : return "Geral";
      case "finance" : return "Financeiro";
      case "tech" : return "Tecnologia";
      case "constr" : return "Construção";
      case "agrib" : return "Agronegócio";
      case "foodDrink" : return "Alimentos e bebidas";
      case "educ" : return "Educação";
      case "eletr" : return "Elétrico";
      case "mall" : return "Shopping";
      case "miniSteel" : return "Mineração e Siderurgia";
    }
    return ''
  }
}
