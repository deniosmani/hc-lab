import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Patient } from 'src/app/core/models/patient.model';
import { Gender } from 'src/app/core/models/practitioner.model';

import { HttpPatientService } from 'src/app/core/services/http-patient.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header.directive';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {


  patients?:Patient[];
  availablePageSize = [2, 5, 10, 15, 20];
  currentPage=1;
  totalItems=10;
  pageSize=5;
  sortOrder='asc';
  sortBy='name';
  
  gender='';
  name='';
  genders?:Gender[];
  @ViewChildren(SortableHeaderDirective)
  headers?:QueryList<SortableHeaderDirective>
  genderForm?:FormGroup;
  filterForm?:FormGroup;
  constructor(private fb:FormBuilder ,private httpPatient:HttpPatientService,private router:Router) { }
  onPageSizeChange(){
    this.loadPatients();
  }
  ngOnInit(): void {
    this.httpPatient.getGenders().pipe(take(1)).subscribe(genders=>{this.genders=genders;this.loadPatients();this.createFilterForm()});
  }
  loadPatients(){
    this.httpPatient.getByPage(this.name,this.gender,this.currentPage,this.pageSize,this.sortBy,this.sortOrder).subscribe(
      patientPage => {
        this.patients=patientPage.content;
        this.totalItems=patientPage.totalElements;
        this.pageSize=patientPage.size;
      }
    )
  }
  onPageChange(page: number) {
    this.loadPatients();
  }
  addPatient(){
    this.router.navigate(['patient/patient-add']);
  }
  onDelete(patient:Patient){
    if(confirm("Are you sure?")){
    this.httpPatient.deletePatient(patient).subscribe((patient=>{this.loadPatients();}),(error:HttpErrorResponse)=>{alert(error.error)});}
  }
  onDetails(patient:Patient){
    this.router.navigate(['patient/patient-details/',patient.id]);
  }
  onEdit(patient:Patient){
    this.router.navigate(['patient/patient-update',patient.id]);
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
    this.loadPatients();
  }

  createFilterForm(){
    this.filterForm=this.fb.group({
      gender:['all'],
      name:['']
    })
  }

  onSubmitForm(){

    this.gender=this.filterForm?.controls['gender']?.value.value;
    this.name=this.filterForm?.controls['name'].value;
    this.loadPatients();
  }
}
