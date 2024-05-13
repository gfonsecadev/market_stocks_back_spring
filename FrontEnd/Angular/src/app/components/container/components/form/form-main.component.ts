import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, NonNullableFormBuilder, UntypedFormArray, UntypedFormBuilder, UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { MyStocksService } from '../../services/my-stocks.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Stock } from '../../models/stock';
import { Profit } from '../../models/profit';
import { ValidateFieldsService } from 'src/app/share/validator/validate-fields.service';

@Component({
  selector: 'app-formulario',
  templateUrl: './form-main.component.html',
  styleUrls: ['./form-main.component.scss']
})
export class FormMainComponent implements OnInit{
  headerTitle:string='';
  service:MyStocksService;
  //form:FormGroup;// essa será a instância para manipular formularios reativos
  form!:FormGroup;
  //A utilização do sinal ! nos permite declarar uma variavel sem a iniciar no mesmo momento


  constructor(private formBuilder:NonNullableFormBuilder,
              private routeActive:ActivatedRoute,
              private router:Router,
              private location:Location,
              service:MyStocksService,
              private snackBar: MatSnackBar,
              public validatorService:ValidateFieldsService) {
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
      name: [stock.name,[Validators.required,Validators.minLength(3),Validators.maxLength(100)]], // ou new FormControl <String>('')aqui se declara os campos do form com seus tipos
      category:  new FormControl <string>(stock.category,{nonNullable:true,validators:Validators.required}),
      price_medium: [stock.price_medium,[Validators.required,Validators.min(0.1)]],
      profits: this.formBuilder.array(this.profitsToArray(stock))
    })
  }

  //função para retornar o texto que será exibido no cabeçalho do formulário de acordo se for adição ou edição de ativo
  header(){
    if(this.router.url.includes('edit')){
      return "Editar ativo";
    }else{
      return "Adicionar ativo";
    }
  }


  //Cria formGroup para o ArrayGroup. Se nada vier como parametro ou seja é uma nova inserção será carregado um formGroup vazio, agora se for edição de dados o resolver nos retornará um array de profits e para cada array será criado um formGroup devido a iteração do objeto recebido.
  public profitsToArray(stock:Stock){
      const arrayProfit=[]


      //percorrer o objeto recebido para criar a quantidade de formGroup correta.
      if(stock.profits){
         stock.profits.forEach((x)=>{
         arrayProfit.push(this.makeFieldBlank(x))
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
                            type:[profit.type,Validators.required],
                            valueDistributed:[profit.valueDistributed,[Validators.required,Validators.min(0.05)]]
                          })
  }
    //esse metodo chama um serviço que faz uma condicional para saber se chama o serviço de adicionar ou editar.
    submitForm(){
      if(this.form.valid){
        //Se estiver validado
        this.service.addOrEdit(this.form.value).subscribe({
          next:()=>this.backPag(),//resposta se sucesso retorna para pagina anterior.
          error:this.error //função que criei para retornar um aviso
        });
      }

    }

    //snacbar de erro
    error(){
      this.snackBar.open("Erro ao cadastrar","",{duration:3000})
    }
    //metodo para voltar pagina anterior
    backPag(){
      this.location.back();
    }

    //função usada pelo mat-error para apresentar o erro de validação dos campos.


      //Retorna o array de lucros para ser iterado no for no html.
      iterarArray(){
       return (<UntypedFormArray>this.form.get("profits")).controls
      }


      addProfitField(){
       const addFieldProfit= this.form.get('profits') as FormArray
       addFieldProfit.push(this.makeFieldBlank())
      }

      deleteProfitField(index:number){
        const deleteFieldProfit=this.form.get('profits') as FormArray
        deleteFieldProfit.removeAt(index)
      }



}
