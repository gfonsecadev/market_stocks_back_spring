import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainContainerModule } from './components/container/main-container.module';
import { NavModule } from './components/nav/nav.module';
import { MaterialForAllModule } from './share/material-for-all.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NavModule,//criado para os componentes do topo da página
    MainContainerModule,//criado para os components do conteúdo da página,
    MaterialForAllModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
