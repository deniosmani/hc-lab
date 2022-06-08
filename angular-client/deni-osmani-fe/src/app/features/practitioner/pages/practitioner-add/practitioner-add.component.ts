import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { take } from 'rxjs';
import { Organization } from 'src/app/core/models/organization.model';
import { Gender, Practitioner, Qualification } from 'src/app/core/models/practitioner.model';
import { HttpPractitionerService } from 'src/app/core/services/http-practitioner.service';

@Component({
  selector: 'app-practitioner-add',
  templateUrl: './practitioner-add.component.html',
  styleUrls: ['./practitioner-add.component.css']
})
export class PractitionerAddComponent implements OnInit {

  model?:NgbDateStruct
  practitioner?:Practitioner;
  organizations?:Organization[];
  practitionerForm?:FormGroup;
  genders?:Gender[];
  qualifications?:Qualification[];

  constructor(private router:Router,private http: HttpPractitionerService,private fb:FormBuilder) { }

  createForm(){
    this.practitionerForm=this.fb.group({
      id:[-1],
      name:[this.practitioner?.name,[Validators.required,Validators.minLength(3)]],
      gender:[this.practitioner?.gender],
      birthDay:[this.practitioner?.birthDay,Validators.required],
      address:[this.practitioner?.address],
      phone:[this.practitioner?.phone],
      surname:[this.practitioner?.surname,[Validators.required,Validators.minLength(3)]],
      email:[this.practitioner?.email,Validators.email],
      qualification:[this.practitioner?.qualification,Validators.required],
      organization:[null]
    })
  }
  ngOnInit(): void {
    this.http.getGenders().pipe(take(1)).subscribe(genders=>this.genders=genders);
    this.http.getQualifications().pipe(take(1)).subscribe(qualifications=>this.qualifications=qualifications);
    this.http.getAllOrganizations().pipe(take(1)).subscribe(organizations=>{this.organizations=organizations;this.createForm();});
  }
  save(){
    if(this.practitionerForm?.controls['birthDay'].value){
    let ngbDate= this.practitionerForm?.controls['birthDay'].value;
    let myDate=new Date(ngbDate.year,ngbDate.month-1,ngbDate.day);
    let formValues=this.practitionerForm?.value;
    formValues['birthDay']=myDate;
    this.practitioner=formValues;}
    this.practitioner=this.practitionerForm?.value;
    console.log(this.practitioner);
    this.http.save(this.practitioner!).subscribe(response=>{this.router.navigate(['practitioner/practitioner-list']);})
  }
  hasErrors(componentName: string, errorCode?: string) {
    return  (this.practitionerForm?.get(componentName)?.dirty || this.practitionerForm?.get(componentName)?.touched ) &&
    ((!errorCode && this.practitionerForm?.get(componentName)?.errors ) ||
    (errorCode && this.practitionerForm?.get(componentName)?.hasError(errorCode)));
  }
  onCancel(){
    this.router.navigate(['practitioner/practitioner-list'])
  }
}
