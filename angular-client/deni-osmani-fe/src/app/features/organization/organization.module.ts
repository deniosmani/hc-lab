import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrganizationRoutingModule } from './organization-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { OrganizationListComponent } from './pages/organization-list/organization-list.component';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';
import { OrganizationAddComponent } from './pages/organization-add/organization-add.component';
import { OrganizationUpdateComponent } from './pages/organization-update/organization-update.component';


@NgModule({
  declarations: [
    OrganizationListComponent,
    OrganizationDetailsComponent,
    OrganizationAddComponent,
    OrganizationUpdateComponent
  ],
  imports: [
    CommonModule,
    OrganizationRoutingModule,
    SharedModule
  ]
})
export class OrganizationModule { }
