import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Organization, OrganizationType } from 'src/app/core/models/organization.model';
import { HttpOrganizationService } from 'src/app/core/services/http-organization.service';

@Component({
  selector: 'app-organization-update',
  templateUrl: './organization-update.component.html',
  styleUrls: ['./organization-update.component.css']
})
export class OrganizationUpdateComponent implements OnInit {

  organization?:Organization;
  organizationForm?:FormGroup;
  organizationTypes?:OrganizationType[]
  names?:string[];

  constructor(private router:Router,private http: HttpOrganizationService,private activeRoute:ActivatedRoute,private fb:FormBuilder) { }

  ngOnInit(): void {
    const id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    this.http.getAllOrgType().pipe(take(1)).subscribe(orgTypes=>this.organizationTypes=orgTypes);
    this.http.getNames().pipe(take(1)).subscribe(names=>{this.names=names;console.log(this.names);});
    this.http.findById(id).subscribe((organization)=>{this.organization=organization;this.createForm();});
  }
  createForm(){
    this.organizationForm=this.fb.group({
      id:[this.organization?.id],
      organizationType:[this.organization?.organizationType?.value,Validators.required],
      name:[this.organization?.name,[Validators.required,Validators.minLength(5)]],//TODO unique
      address:[this.organization?.address],
      phone:[this.organization?.phone],
      email:[this.organization?.email,Validators.email]
    })
  }
  update(){
    this.organization=this.organizationForm?.value;
    this.http.update(this.organization!).subscribe((organization)=>this.router.navigate(['/organization/organization-list']))
  }
  hasErrors(componentName: string, errorCode?: string) {
    return  (this.organizationForm?.get(componentName)?.dirty || this.organizationForm?.get(componentName)?.touched || this.organizationForm?.get(componentName)?.invalid ) &&
    ((!errorCode && this.organizationForm?.get(componentName)?.errors ) ||
    (errorCode && this.organizationForm?.get(componentName)?.hasError(errorCode)));
  }
  checkName(){
    let name=this.organizationForm?.controls['name'].value;
    return !this.names?.includes(name)||name==this.organization?.name

  }
  onCancel(){
    this.router.navigate(['organization/organization-list'])
  }
}
