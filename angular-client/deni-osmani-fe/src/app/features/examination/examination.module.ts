import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExaminationRoutingModule } from './examination-routing.module';
import { ExaminationListComponent } from './pages/examination-list/examination-list.component';
import { ExaminationDetailsComponent } from './pages/examination-details/examination-details.component';
import { ExaminationAddComponent } from './pages/examination-add/examination-add.component';
import { ExaminationUpdateComponent } from './pages/examination-update/examination-update.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    ExaminationListComponent,
    ExaminationDetailsComponent,
    ExaminationAddComponent,
    ExaminationUpdateComponent
  ],
  imports: [
    CommonModule,
    ExaminationRoutingModule,
    SharedModule
  ]
})
export class ExaminationModule { }
