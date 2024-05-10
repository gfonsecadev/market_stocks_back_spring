import { Injectable } from '@angular/core';
import {

  RouterStateSnapshot,
  ActivatedRouteSnapshot,
  Resolve
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Stock } from '../models/stock';
import { MyStocksService } from '../services/my-stocks.service';

@Injectable({
  providedIn: 'root'
})
export class EditResolver implements Resolve<Stock> {
  constructor(private service:MyStocksService){}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Stock> {
    if(route.params && route.params['id']){
      //Se passar retornará um observable para ser subscrito
     return this.service.searchForEditStock(route.params['id']);
    }
    //Se nada for encontrado retornará um observable vazio da classe Stock
    return of({id:'', name:'', price_medium:0, category:''});
  }
}
