import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExaminationAddComponent } from './pages/examination-add/examination-add.component';
import { ExaminationDetailsComponent } from './pages/examination-details/examination-details.component';
import { ExaminationListComponent } from './pages/examination-list/examination-list.component';
import { ExaminationUpdateComponent } from './pages/examination-update/examination-update.component';

const routes: Routes = [
  {path: 'examination-list', component: ExaminationListComponent},
  {path:'examination-details/:id', component:ExaminationDetailsComponent},
  {path:'examination-add',component:ExaminationAddComponent},
  {path:'examination-update/:id',component:ExaminationUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExaminationRoutingModule { }
