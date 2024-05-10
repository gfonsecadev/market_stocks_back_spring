import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NavMainComponent } from './nav-main/nav-main.component';
import { MaterialForAllModule } from 'src/app/share/material-for-all.module';

@NgModule({
  declarations: [
    NavMainComponent // aqui estarão os componentes deste modulo
  ],
  imports: [
    CommonModule,
    MaterialForAllModule

  ],
  exports:[NavMainComponent]
  // exportar para que componentes de outros modulos tenham acesso
})
export class NavModule { }
