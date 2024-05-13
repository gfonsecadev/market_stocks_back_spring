import { Component, OnInit } from '@angular/core';
import { MyStocksService } from '../../services/my-stocks.service';
import { FormArray, FormControl, FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ValidateFieldsService } from 'src/app/share/validator/validate-fields.service';
import { Profit } from '../../models/profit';
import { Stock } from '../../models/stock';
import { Location } from '@angular/common';
import { CategoryPipe } from 'src/app/share/pipes/category/category.pipe';
import { ProfitTypePipe } from 'src/app/share/pipes/profitType/profit-type.pipe';

@Component({
  selector: 'app-card-show',
  templateUrl: './card-show.component.html',
  styleUrls: ['./card-show.component.scss'],
})
export class CardShowComponent implements OnInit {



  service:MyStocksService;
  //form:FormGroup;// essa será a instância para manipular formularios reativos
  form!:FormGroup;
  //A utilização do sinal ! nos permite declarar uma variavel sem a iniciar no mesmo momento


  constructor(private formBuilder:NonNullableFormBuilder,
              private routeActive:ActivatedRoute,
              private location:Location,
              service:MyStocksService,
              public validatorService:ValidateFieldsService,
              public categoryPipe:CategoryPipe,
              public profitTypePipe:ProfitTypePipe) {
               /* Aqui teremos instância sem tipagem do formulario.
                 this.form=this.formBuilder.group({
                  name: [''],
                  category: [null],
                  price_medium: new FormControl <Number>(0.0)
                }) */
              this.service=service;

  }


  ngOnInit(): void {
    const stock:Stock=this.routeActive.snapshot.data['stock'];
    this.form=this.formBuilder.group({
      id: stock.id,
      name: [stock.name], // ou new FormControl <String>('')aqui se declara os campos do form com seus tipos
      category:  new FormControl <string>(this.categoryPipe.transform(stock.category)),
      price_medium: [`R$ ${stock.price_medium}`],
      profits: this.formBuilder.array(this.profitsToArray(stock))
    })

  }


  //Cria formGroup para o ArrayGroup. Se nada vier como parametro ou seja é uma nova inserção será carregado um formGroup vazio, agora se for edição de dados o resolver nos retornará um array de profits e para cada array será criado um formGroup devido a iteração do objeto recebido.
  public profitsToArray(stock:Stock){
      const arrayProfit=[]


      //percorrer o objeto recebido para criar a quantidade de formGroup correta.
      if(stock.profits){
         stock.profits.forEach((x)=>{
         arrayProfit.push(this.makeFieldBlank(x))
         console.log(x)
      })
    }else{
        arrayProfit.push(this.makeFieldBlank())
    }
      //No final me retorna um array de formGroup de acordo com a quantidade recebida
      return arrayProfit
  }
//função que retorna o esqueleto do formGroup para o FormArray
makeFieldBlank(profit:Profit={ id:'', type:'', valueDistributed:0}){
    return this.formBuilder.group({
                            id:[profit.id,],
                            type:[this.profitTypePipe.transform(profit.type)],
                            valueDistributed:[`R$ ${profit.valueDistributed}`]
                          })
  }


    //metodo para voltar pagina anterior
    backPag(){
      this.location.back();
    }



      //Retorna o array de lucros para ser iterado no for no html.
      iterarArray(){
        return (<UntypedFormArray>this.form.get("profits")).controls
      }
      //função que retorna true para exibir os lucros se o formulário contiver valor e false para exibir um template padrão
     show(){
      if(this.form.get("profits")?.value!=''){
        return true
      }
      return false
     }
}
