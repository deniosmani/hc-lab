import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Organization, OrganizationType } from 'src/app/core/models/organization.model';
import { HttpOrganizationService } from 'src/app/core/services/http-organization.service';

@Component({
  selector: 'app-organization-add',
  templateUrl: './organization-add.component.html',
  styleUrls: ['./organization-add.component.css']
})
export class OrganizationAddComponent implements OnInit {

  organization?:Organization;
  organizationTypes?:OrganizationType[]
  organizationForm?:FormGroup;
  names?:string[];

  constructor(private httpOrganization:HttpOrganizationService,private router:Router,private http: HttpOrganizationService, private fb:FormBuilder) { }

  ngOnInit(): void {
    this.httpOrganization.getNames().pipe(take(1)).subscribe(names=>{this.names=names;});
    this.http.getAllOrgType().pipe(take(1)).subscribe(orgTypes=>{this.organizationTypes=orgTypes;this.createForm()})
  }
  createForm(){
    this.organizationForm=this.fb.group({
      id:[-1],
      organizationType:[this.organization?.organizationType,[Validators.required]],
      name:[this.organization?.name,[Validators.required,Validators.minLength(5)]],//TODO unique
      address:[this.organization?.address],
      phone:[this.organization?.phone],
      email:[this.organization?.email,Validators.email]
    })
  }
  save(){
    this.organization=this.organizationForm?.value;
    this.http.save(this.organization!).subscribe(organization=>{this.router.navigate(['organization/organization-list'])})
  }
  hasErrors(componentName: string, errorCode?: string) {
    return  (this.organizationForm?.get(componentName)?.dirty || this.organizationForm?.get(componentName)?.touched  ) &&
    ((!errorCode && this.organizationForm?.get(componentName)?.errors ) ||
    (errorCode && this.organizationForm?.get(componentName)?.hasError(errorCode)));
  }
  checkName(){
    let name=this.organizationForm?.controls['name'].value;
    return !this.names?.includes(name)

  }
  onCancel(){
    this.router.navigate(['organization/organization-list'])
  }
}
