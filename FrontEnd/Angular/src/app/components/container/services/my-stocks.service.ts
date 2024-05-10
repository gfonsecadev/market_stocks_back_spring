import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http'
import { Stock } from '../models/stock';
import { catchError, delay, first, map, of, tap } from 'rxjs';
import { StockPagination } from '../models/stockPagination';

@Injectable({
  providedIn: 'root'
})
export class MyStocksService {
  //private readonly url:string ="./assets/date.json"  //para teste
  private readonly url:string = "/api/stocks"

  constructor(private service:HttpClient) {
  }

  //metodo que popula.
  getStocks(pag:number=0, size:number=10){
    return this.service.get<StockPagination>(this.url,{params:{pag,size}}).pipe(
      first(),//para observar apenas uma vez e logo fazer o subscribe

      //tap((resp)=>console.log(resp)),//para manipular a resposta alterar ou debugar

      delay(0),//aplica um delay na resposta http.

     /*  catchError(error=>{//Trata os erros da requisição,por padrão returna um observable .
        //Aqui você pode colocar um dialog ou algo para avisar o usuário que deu erro.
        return of([])
        //O operador of serve para transformar um objeto em um observable.
      })  */
      )

  }

  //será utilizada no metodo addOrEdit
  private addStock(stock:Partial<Stock>){
    return this.service.post<Stock>(this.url,stock).pipe(
      first()//Só para dizer que será feita somente uma vez
      )
  }
  //será utilizada no metodo addOrEdit
  private editStock(stock:Partial<Stock>){
    return this.service.put<Stock>(`${this.url}/${stock.id}`,stock).pipe(
      first()
    )
  }
  /* metodo que verifica se o formulario do componente tem id preenchido,
  se houver significa que é uma edição de dados se não é adiçaõ de dados */
  addOrEdit(stock:Partial<Stock>){

    if(stock.id==''){
      //chama a funçaõ de adição deste service para ser retornada
     return this.addStock(stock)

    }
    //chama a funçaõ de edição deste service  para ser retornada
    return this.editStock(stock)
  }

  //metodo para buscar um dado no backend para edição
  searchForEditStock(id:string){
    return this.service.get<Stock>(`${this.url}/${id}`).pipe(
      first()
    )
  }

  //metodo para apagar dado no backend.
  deleteStock(id:string){
    return this.service.delete(`${this.url}/${id}`).pipe(
      first()
    )
  }
}
