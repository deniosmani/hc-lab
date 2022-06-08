import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PractitionerRoutingModule } from './practitioner-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { PractitionerListComponent } from './pages/practitioner-list/practitioner-list.component';
import { PractitionerDetailsComponent } from './pages/practitioner-details/practitioner-details.component';
import { PractitionerAddComponent } from './pages/practitioner-add/practitioner-add.component';
import { PractitionerUpdateComponent } from './pages/practitioner-update/practitioner-update.component';


@NgModule({
  declarations: [
    PractitionerListComponent,
    PractitionerDetailsComponent,
    PractitionerAddComponent,
    PractitionerUpdateComponent
  ],
  imports: [
    CommonModule,
    PractitionerRoutingModule,
    SharedModule
  ]
})
export class PractitionerModule { }
