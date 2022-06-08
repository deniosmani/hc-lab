import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {  HttpClientModule } from '@angular/common/http';
import { NgbDropdown, NgbDropdownModule, NgbModule, NgbNav, NgbNavModule, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { HeaderComponent } from './components/header/header.component';
import { SortableHeaderDirective } from './directives/sortable-header.directive';
import { IconsModule } from './icons/icons.module';


@NgModule({
  declarations: [
    HeaderComponent,
    SortableHeaderDirective
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
    NgbNavModule,
    NgbPaginationModule,
    NgbModule,
    NgbDropdownModule,
    NgMultiSelectDropDownModule.forRoot()
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
    NgbPaginationModule,
    NgbModule,
    NgbNavModule,
    NgbDropdownModule,
    NgMultiSelectDropDownModule,
    HeaderComponent,
    SortableHeaderDirective,
    IconsModule
  ]
})
export class SharedModule { }
