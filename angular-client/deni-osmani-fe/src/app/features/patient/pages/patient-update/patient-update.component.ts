import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Organization } from 'src/app/core/models/organization.model';
import { MartialStatus, Patient } from 'src/app/core/models/patient.model';
import { Gender, Practitioner } from 'src/app/core/models/practitioner.model';
import { HttpPatientService } from 'src/app/core/services/http-patient.service';

@Component({
  selector: 'app-patient-update',
  templateUrl: './patient-update.component.html',
  styleUrls: ['./patient-update.component.css']
})
export class PatientUpdateComponent implements OnInit {

  patient?:Patient;
  organizations?:Organization[];
  genders?:Gender[];
  practitioners?:Practitioner[];
  martialStatuses?:MartialStatus[];
  patientForm?:FormGroup;

  constructor(private router:Router,private activeRoute:ActivatedRoute,private http: HttpPatientService,private fb:FormBuilder) { }

  ngOnInit(): void {
    const id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    this.http.getGenders().pipe(take(1)).subscribe(genders=>this.genders=genders);
    this.http.getMartialStatuses().pipe(take(1)).subscribe(martialStatuses=>this.martialStatuses=martialStatuses);
    this.http.getAllOrganizations().pipe(take(1)).subscribe(organizations=>{this.organizations=organizations;});
    this.http.findById(id).subscribe(patient=>{this.patient=patient;this.http.getAllPractitioners(this.patient?.organization?.id!).subscribe(practitioners=>{this.practitioners=practitioners;});this.createForm();})
    console.log('ngOnInit',"done");

  }
  createForm(){

    this.patientForm=this.fb.group({
      id:[this.patient?.id],
      name:[this.patient?.name,[Validators.required,Validators.minLength(3)]],
      gender:[this.patient?.gender?.value],
      deceased:[this.patient?.deceased],
      birthDate:[this.patient?.birthDate,Validators.required],
      address:[this.patient?.address],
      phone:[this.patient?.phone],
      surname:[this.patient?.surname,[Validators.required,Validators.minLength(3)]],
      email:[this.patient?.email,Validators.email],
      martialStatus:[this.patient?.martialStatus?.value],
      organization:[this.patient?.organization],
      primaryCareProvider:[this.patient?.primaryCareProvider]
    })
  }
  organizationSelected(event:Event){
    this.http.getAllPractitioners(this.patientForm?.controls['organization'].value.id).pipe(take(1)).subscribe(practitioners=>{this.practitioners=practitioners;})
  }
  save(){
    this.patient=this.patientForm?.value;
    console.log("patient",this.patient);
    this.http.update(this.patient!).subscribe(response=>{this.router.navigate(['patient/patient-list'])});
  }
  hasErrors(componentName: string, errorCode?: string) {
    return  (this.patientForm?.get(componentName)?.dirty || this.patientForm?.get(componentName)?.touched || this.patientForm?.get(componentName)?.invalid ) &&
    ((!errorCode && this.patientForm?.get(componentName)?.errors ) ||
    (errorCode && this.patientForm?.get(componentName)?.hasError(errorCode)));
  }
  compareByOrgID(item1: Organization, item2: Organization) {
    return item1 && item2 && item1.id == item2.id;
  }
  compareByPracID(item1: Practitioner, item2: Practitioner) {
    return item1 && item2 && item1.id == item2.id;
  }
  onCancel(){
    this.router.navigate(['patient/patient-list'])
  }
}
