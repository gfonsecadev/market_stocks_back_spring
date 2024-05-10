import { Component, Input, ViewChild } from '@angular/core';
import { Stock } from './models/stock';
import { Observable, catchError, of, tap } from 'rxjs';
import { MyStocksService } from './services/my-stocks.service';
import { MatDialog } from '@angular/material/dialog';
import { DialogErrorComponent } from 'src/app/share/components/dialog-error/dialog-error.component';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogDeleteComponent } from 'src/app/share/components/dialog-delete/dialog-delete.component';
import { StockPagination } from './models/stockPagination';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-main-container',
  templateUrl: './main-container.component.html',
  styleUrls: ['./main-container.component.scss']
})
export class MainContainerComponent {
  // Esta variavel deve ser do tipo observable para pode ser passada para o pipe assinc
  stocks$?:Observable<StockPagination>;

  //variaveis de paginação
  stockPag:number=0;
  stockSize:number=10;
  stockLenght!:number;

  @ViewChild(MatPaginator) matPaginator?:MatPaginator;

  constructor(public servico:MyStocksService,
              public dialog:MatDialog,
              public router:Router,//objeto de roteamento
              public routeActive:ActivatedRoute,
              public snackBar:MatSnackBar) {
              this.listData();

}



    listData(pageEvent:PageEvent={pageIndex:0,pageSize:10,length:0}){
       this.stocks$=this.servico.getStocks(pageEvent.pageIndex,pageEvent.pageSize).pipe(
       tap((x)=>{
        this.stockLenght=x.totalData;
        this.stockPag=pageEvent.pageIndex;
        this.stockSize=pageEvent.pageSize;
       }),
       catchError(error => {
                            this.errorRequest("Erro ao buscar informações")
                            return of({stockPag:[],totalData:0})//of para retornar um obsrvable vazio
                           }))
    }

    //Metodo criado para chamar o dialog
    errorRequest(mensage:string){
      this.dialog.open(DialogErrorComponent,{data:mensage})
     }

     addStock(){
      this.router.navigate(['new'],{relativeTo:this.routeActive})
      //será redirecionado para a rota atual definida no arquivo de rota + new.
   }

     searchEditStock(stock:Stock){
      this.router.navigate(['edit',stock.id],{relativeTo:this.routeActive})
   }

    deleteStock(stock:Stock){
      const dialogRef= this.dialog.open(DialogDeleteComponent, {
        data: "Tem certeza que deseja excluir?",
      });

      dialogRef.afterClosed().subscribe(result => {
        if(result){
          this.servico.deleteStock(stock.id).subscribe({
            next:()=>{  this.listData();
                       this.snackBar.open("Sucesso ao deletar dados!","",
                                          {duration:5000,horizontalPosition:'center'
                                          ,verticalPosition:'top',}) },
            error: ()=>{ this.errorRequest('Erro ao deletar dado!') }
          })
        }
      });
    }

}
