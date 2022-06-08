import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientRoutingModule } from './patient-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { PatientListComponent } from './pages/patient-list/patient-list.component';
import { PatientDetailsComponent } from './pages/patient-details/patient-details.component';
import { PatientAddComponent } from './pages/patient-add/patient-add.component';
import { PatientUpdateComponent } from './pages/patient-update/patient-update.component';


@NgModule({
  declarations: [
    PatientListComponent,
    PatientDetailsComponent,
    PatientAddComponent,
    PatientUpdateComponent
  ],
  imports: [
    CommonModule,
    PatientRoutingModule,
    SharedModule
  ]
})
export class PatientModule { }
