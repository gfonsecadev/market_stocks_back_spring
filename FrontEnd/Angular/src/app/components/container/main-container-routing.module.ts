import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FormMainComponent } from './components/form/form-main.component';
import { MainContainerComponent } from './main-container.component';
import { EditResolver } from './guards/edit.resolver';

const routes: Routes = [
  {path:'',component:MainContainerComponent},
  {path:'new', component:FormMainComponent, resolve:{stock:EditResolver}},
  {path:'edit/:id', component:FormMainComponent, resolve:{stock:EditResolver}}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],//forChild porque será carregado em Lazy loading
  exports: [RouterModule]
})
export class MainContainerRoutingModule { }
