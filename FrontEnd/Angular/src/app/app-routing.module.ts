import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:'', redirectTo:'stocks', pathMatch:'full'},
  //lazy loading(carregamento por pacotes no Angular)
  {path:'stocks', loadChildren: ()=>import('./components/container/main-container.module')
                                    .then((modulo)=>modulo.MainContainerModule)}
                                    //Estará carregando as rotas do modulo acima(Container) em lazy loading
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
