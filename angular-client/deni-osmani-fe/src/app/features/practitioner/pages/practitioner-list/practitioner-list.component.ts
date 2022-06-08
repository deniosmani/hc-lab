import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Practitioner } from 'src/app/core/models/practitioner.model';
import { HttpPractitionerService } from 'src/app/core/services/http-practitioner.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header.directive';

@Component({
  selector: 'app-practitioner-list',
  templateUrl: './practitioner-list.component.html',
  styleUrls: ['./practitioner-list.component.css']
})
export class PractitionerListComponent implements OnInit {

  practitioners?:Practitioner[]
  availablePageSize = [2, 5, 10, 15, 20];
  availableValues=["unassigned","all"];
  currentPage=1;
  totalItems=10;
  pageSize=5;
  sortOrder='asc';
  sortBy='name';
  unassigned='';
  name='';
  filterForm?:FormGroup;
  constructor(private fb:FormBuilder, private httpPractitioner:HttpPractitionerService,private router:Router) { }
  @ViewChildren(SortableHeaderDirective)
  headers?:QueryList<SortableHeaderDirective>
  ngOnInit(): void {
    this.createForm();
    this.loadPractitioners();
  }
  onPageSizeChange(){
    this.loadPractitioners();
  }
  onEdit(practitioner:Practitioner){
    this.router.navigate(['practitioner/practitioner-update',practitioner.id]);
  }
  onDetails(practitioner:Practitioner){
    this.router.navigate(['practitioner/practitioner-details/',practitioner.id]);
  }
  onDelete(practitioner:Practitioner){
    if(confirm("Are you sure?")){
    this.httpPractitioner.deletePractitioner(practitioner).subscribe((organization=>{this.loadPractitioners();}),(error:HttpErrorResponse)=>{alert(error.error)});}
  }
  addPractitioner(){
    this.router.navigate(['practitioner/practitioner-add']);
  }
  loadPractitioners(){
    console.log("unassigned",this.unassigned);
    this.httpPractitioner.getByPage(this.name,this.unassigned,this.currentPage,this.pageSize,this.sortBy,this.sortOrder).subscribe(
      organizationPage => {
        this.practitioners=organizationPage.content;
        this.totalItems=organizationPage.totalElements;
        this.pageSize=organizationPage.size;
      }
    )
  }
  onPageChange(page: number) {
    this.loadPractitioners();
  }
  onSort(sortEvent:SortEvent){
    console.log('sort event:',sortEvent);
    this.sortBy=sortEvent.column;
    this.sortOrder=sortEvent.direction;
    this.headers?.forEach(
      header=>{
        if(header.sortable!==sortEvent.column){
          header.direction='';
        }
      }
    )
    this.loadPractitioners();
  }

  createForm(){
    this.filterForm=this.fb.group({
      unassigned:['all'],
      name:['']
    })
  }
  onSubmitForm(){
    this.unassigned=this.filterForm?.controls['unassigned'].value;
    this.name=this.filterForm?.controls['name'].value;
    console.log(this.unassigned,this.name);
    this.loadPractitioners();
  }
}
