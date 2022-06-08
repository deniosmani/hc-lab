import { Directive, EventEmitter, Input, Output } from '@angular/core';

export type SortDirection='asc'|'desc'|''

export interface SortEvent{
  column:string;
  direction:SortDirection;

}

@Directive({
  selector: 'th[sortable]',
  host:{
    '(click)':'changeSortDirection()',//when there is click on host,call changeSort()
    '[class.asc]': 'direction === "asc"',//put clas asc if direction is asc
    '[class.desc]': 'direction === "desc"'
  }
})
export class SortableHeaderDirective {

  @Input() sortable: string='';
  @Input() direction: SortDirection = 'asc';
  @Output() sort= new EventEmitter<SortEvent>();

  constructor() {
    console.log('Sortable directive constructor');
  }
  changeSortDirection(){
    if(this.direction==='asc'){
      this.direction='desc';
    } else {
      this.direction='asc';
    }
    this.sort.emit({column:this.sortable,direction:this.direction});
  }
}
