import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogErrorComponent } from './components/dialog-error/dialog-error.component';
import { MaterialForAllModule } from './material-for-all.module';
import { IconPipe } from './pipes/icon.pipe';
import { DialogDeleteComponent } from './components/dialog-delete/dialog-delete.component';


@NgModule({
  declarations: [
    DialogErrorComponent,
    IconPipe,
    DialogDeleteComponent,

  ],
  imports: [
    CommonModule,
    MaterialForAllModule
  ],
  exports:[DialogErrorComponent,IconPipe,]
})
export class ShareModule { }
