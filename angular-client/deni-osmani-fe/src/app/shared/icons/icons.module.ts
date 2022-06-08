import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BootstrapIconsModule } from 'ng-bootstrap-icons';
import { Search,Trash,CardText,Pencil,Plus } from 'ng-bootstrap-icons/icons';
const icons = {
  Search, Trash,CardText,Pencil,Plus
};

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    BootstrapIconsModule.pick(icons)
  ],
  exports:[
    BootstrapIconsModule
  ]
})
export class IconsModule { }
