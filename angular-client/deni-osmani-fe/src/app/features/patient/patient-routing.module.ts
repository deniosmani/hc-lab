import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientAddComponent } from './pages/patient-add/patient-add.component';
import { PatientDetailsComponent } from './pages/patient-details/patient-details.component';
import { PatientListComponent } from './pages/patient-list/patient-list.component';
import { PatientUpdateComponent } from './pages/patient-update/patient-update.component';

const routes: Routes = [
  {path: 'patient-list', component: PatientListComponent},
  {path:'patient-details/:id', component:PatientDetailsComponent},
  {path:'patient-add',component:PatientAddComponent},
  {path:'patient-update/:id',component:PatientUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientRoutingModule { }
