import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MainContainerRoutingModule } from './main-container-routing.module';
import { MainContainerComponent } from './main-container.component';
import { RouterModule } from '@angular/router';
import { TableComponent } from './components/table/table.component';
import { MaterialForAllModule } from '../../share/material-for-all.module';
import { HttpClientModule } from '@angular/common/http';
import { ShareModule } from 'src/app/share/share.module';
import { FormMainComponent } from './components/form/form-main.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CardShowComponent } from './components/card-show/card-show.component';

//Modulo para carregar os componentes do container
@NgModule({
  declarations: [MainContainerComponent, TableComponent, FormMainComponent, CardShowComponent],
  imports: [
    CommonModule,
    MainContainerRoutingModule,//Modulo principal do mainContainer que será importado pelo app
    RouterModule,//Modulo de roteamento do Angular
    HttpClientModule,// Importar para que os componentes tenham acesso ao modulo http;
    MaterialForAllModule,//Criei um modulo somente para as importações dos componentes do Angular Material
    ReactiveFormsModule,//modulo para trabalhar com formularios reativos.
    FormsModule,//moduulo para trabalhar com formularios
    ShareModule,// modulo que criei que contém componentes que podem ser utilizados em varios outros modulos como o dialogError que criei

  ],
  exports:[MainContainerComponent]
})
export class MainContainerModule { }
