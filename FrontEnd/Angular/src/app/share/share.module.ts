import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogErrorComponent } from './components/dialog-error/dialog-error.component';
import { MaterialForAllModule } from './material-for-all.module';
import { IconPipe } from './pipes/icon/icon.pipe';
import { CategoryPipe } from './pipes/category/category.pipe';
import { DialogDeleteComponent } from './components/dialog-delete/dialog-delete.component';
import { ProfitTypePipe } from './pipes/profitType/profit-type.pipe';


@NgModule({
  declarations: [
    DialogErrorComponent,
    IconPipe,
    CategoryPipe,
    ProfitTypePipe,
    DialogDeleteComponent,

  ],
  imports: [
    CommonModule,
    MaterialForAllModule
  ],
  exports:[DialogErrorComponent,IconPipe,CategoryPipe,ProfitTypePipe,]
})
export class ShareModule { }
