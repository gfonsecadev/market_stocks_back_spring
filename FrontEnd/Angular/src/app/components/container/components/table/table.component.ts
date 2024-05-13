import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Stock } from '../../models/stock';
import { ActivatedRoute, Router } from '@angular/router';
import { StockPagination } from '../../models/stockPagination';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  displayedColumns=['id','name','preco_medio','actions']// variavel com os nomes da coluna
  @Input() stocks:Stock[]=[];
  @Output() show=new EventEmitter(false);
  @Output() add=new EventEmitter(false);//parametro se será asssincrono ou não.
  @Output() edit=new EventEmitter(false);
  @Output() delete=new EventEmitter(false);
  //um evento deste componente será enviado para o componente main-container


  constructor( public router:Router,//objeto de roteamento
               public routeActive:ActivatedRoute){}//dados da rota ativa

   emitShowStock(stock:Stock){
      this.show.emit(stock);
   }
   emitAddStock(){
      this.add.emit(true);
      //será enviado para o componente pai para disparar o evento.
   }

   emitDeleteStock(stock:Stock){
    this.delete.emit(stock);
   }

   emitSearchEditStock(stock:Stock){
      this.edit.emit(stock);
      //será enviado para o componente pai para disparar o evento.
   }


  ngOnInit(): void {

  }

}
