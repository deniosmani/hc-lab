import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PractitionerAddComponent } from './pages/practitioner-add/practitioner-add.component';
import { PractitionerDetailsComponent } from './pages/practitioner-details/practitioner-details.component';
import { PractitionerListComponent } from './pages/practitioner-list/practitioner-list.component';
import { PractitionerUpdateComponent } from './pages/practitioner-update/practitioner-update.component';

const routes: Routes = [
  {path: 'practitioner-list', component: PractitionerListComponent},
  {path:'practitioner-details/:id', component:PractitionerDetailsComponent},
  {path:'practitioner-add',component:PractitionerAddComponent},
  {path:'practitioner-update/:id',component:PractitionerUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PractitionerRoutingModule { }
