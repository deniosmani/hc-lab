import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrganizationAddComponent } from './pages/organization-add/organization-add.component';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';
import { OrganizationListComponent } from './pages/organization-list/organization-list.component';
import { OrganizationUpdateComponent } from './pages/organization-update/organization-update.component';

const routes: Routes = [
  {path: 'organization-list', component: OrganizationListComponent},
  {path:'organization-details/:id', component:OrganizationDetailsComponent},
  {path:'organization-add',component:OrganizationAddComponent},
  {path:'organization-update/:id',component:OrganizationUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrganizationRoutingModule { }
