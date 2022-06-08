import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Organization } from 'src/app/core/models/organization.model';
import { Gender, Practitioner, Qualification } from 'src/app/core/models/practitioner.model';
import { HttpPractitionerService } from 'src/app/core/services/http-practitioner.service';

@Component({
  selector: 'app-practitioner-update',
  templateUrl: './practitioner-update.component.html',
  styleUrls: ['./practitioner-update.component.css']
})
export class PractitionerUpdateComponent implements OnInit {


  practitioner?:Practitioner;
  practitionerForm?:FormGroup;
  organizations?:Organization[];
  genders?:Gender[];
  qualifications?:Qualification[];

  constructor(private router:Router,private http: HttpPractitionerService,private activeRoute:ActivatedRoute,private fb:FormBuilder) { }

  ngOnInit(): void {
    const id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    this.http.getGenders().pipe(take(1)).subscribe(genders=>this.genders=genders);
    this.http.getQualifications().pipe(take(1)).subscribe(qualifications=>this.qualifications=qualifications);
    this.http.getAllOrganizations().pipe(take(1)).subscribe(organizations=>{this.organizations=organizations});
    this.http.findById(id).subscribe((practitioner)=>{this.practitioner=practitioner;this.createForm();});

  }
  createForm(){
    this.practitionerForm=this.fb.group({
      id:[this.practitioner?.id],
      name:[this.practitioner?.name,[Validators.required,Validators.minLength(3)]],
      surname:[this.practitioner?.surname,[Validators.required,Validators.minLength(3)]],
      organization:[this.practitioner?.organization],
      gender:[this.practitioner?.gender?.value],
      birthDay:[this.practitioner?.birthDay,Validators.required],
      address:[this.practitioner?.address],
      phone:[this.practitioner?.phone],
      email:[this.practitioner?.email,Validators.email],
      qualification:[this.practitioner?.qualification?.value,Validators.required]
    })
  }
  update(){
    console.log("practForm",this.practitionerForm?.value)
    if(typeof this.practitionerForm?.controls['organization'].value === 'string'){
      this.practitionerForm.controls['organization'].setValue(null);
    }

    this.practitioner=this.practitionerForm?.value;
    console.log("this.practitioner.after.form",this.practitioner);
    this.http.update(this.practitioner!).subscribe((practitioner)=>{this.router.navigate(['practitioner/practitioner-list'])});
  }
  hasErrors(componentName: string, errorCode?: string) {
    return  (this.practitionerForm?.get(componentName)?.dirty || this.practitionerForm?.get(componentName)?.touched || this.practitionerForm?.get(componentName)?.invalid ) &&
    ((!errorCode && this.practitionerForm?.get(componentName)?.errors ) ||
    (errorCode && this.practitionerForm?.get(componentName)?.hasError(errorCode)));
  }
  compareByOrgID(item1: Organization, item2: Organization) {
    return item1 && item2 && item1.id == item2.id;
  }
  onCancel(){
    this.router.navigate(['practitioner/practitioner-list'])
  }
}
