import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Organization } from 'src/app/core/models/organization.model';
import { MartialStatus, Patient } from 'src/app/core/models/patient.model';
import { Gender, Practitioner } from 'src/app/core/models/practitioner.model';
import { HttpPatientService } from 'src/app/core/services/http-patient.service';

@Component({
  selector: 'app-patient-add',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.css']
})
export class PatientAddComponent implements OnInit {

  isOrganizationSelected?:boolean=false;
  patient?:Patient;
  organizations?:Organization[];
  genders?:Gender[];
  practitioners?:Practitioner[];
  martialStatuses?:MartialStatus[];
  patientForm?:FormGroup;

  constructor(private router:Router,private http: HttpPatientService,private fb:FormBuilder) { }

  ngOnInit(): void {
    console.log(this.isOrganizationSelected);
    this.http.getGenders().pipe(take(1)).subscribe(genders=>this.genders=genders);
    this.http.getMartialStatuses().pipe(take(1)).subscribe(martialStatuses=>this.martialStatuses=martialStatuses);
    this.http.getAllOrganizations().pipe(take(1)).subscribe(organizations=>{this.organizations=organizations;this.createForm();});
  }
  createForm(){
    this.patientForm=this.fb.group({
      id:[-1],
      name:[this.patient?.name,[Validators.required,Validators.minLength(3)]],
      gender:[this.patient?.gender],
      birthDate:[this.patient?.birthDate,Validators.required],
      address:[this.patient?.address],
      phone:[this.patient?.phone],
      surname:[this.patient?.surname,[Validators.required,Validators.minLength(3)]],
      email:[this.patient?.email,Validators.email],
      martialStatus:[this.patient?.martialStatus],
      organization:[null],
      primaryCareProvider:[null]
    })
  }
  save(){
    if(this.patientForm?.controls['birthDate'].value){
      let ngbDate= this.patientForm?.controls['birthDate'].value;
      let myDate=new Date(ngbDate.year,ngbDate.month-1,ngbDate.day);
      let formValues=this.patientForm?.value;
      formValues['birthDate']=myDate;
      this.patient=formValues;}
    this.patient=this.patientForm?.value;
    console.log("patient",this.patient);
    this.http.save(this.patient!).subscribe(response=>{this.router.navigate(['patient/patient-list']);});
  }
  organizationSelected(event:Event){
    this.http.getAllPractitioners(this.patientForm?.controls['organization'].value.id).pipe(take(1)).subscribe(practitioners=>{this.practitioners=practitioners; this.isOrganizationSelected=true;})
  }
  hasErrors(componentName: string, errorCode?: string) {
    return  (this.patientForm?.get(componentName)?.dirty || this.patientForm?.get(componentName)?.touched  ) &&
    ((!errorCode && this.patientForm?.get(componentName)?.errors ) ||
    (errorCode && this.patientForm?.get(componentName)?.hasError(errorCode)));
  }
  onCancel(){
    this.router.navigate(['patient/patient-list'])
  }
}
