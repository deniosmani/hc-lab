import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { take } from 'rxjs';
import { Examination, ExamPriority, ExamStatus, ServiceType } from 'src/app/core/models/examination.model';
import { Organization } from 'src/app/core/models/organization.model';
import { Patient } from 'src/app/core/models/patient.model';
import { Practitioner } from 'src/app/core/models/practitioner.model';
import { HttpExaminationService } from 'src/app/core/services/http-examination.service';


@Component({
  selector: 'app-examination-add',
  templateUrl: './examination-add.component.html',
  styleUrls: ['./examination-add.component.css']
})
export class ExaminationAddComponent implements OnInit {

  dropdownList = [];
  help?:{id:number,text:string}[];
  selectedItems = [];
  dropdownSettings:IDropdownSettings = {
    selectAllText: 'Select All',
    unSelectAllText: 'UnSelect All',
    singleSelection: false,
    idField:'id',
    textField:'name',
    noDataAvailablePlaceholderText:'There is no practitioner in this organization'
  };
  isOrganizationSelected?:boolean=false;
  examination?:Examination;
  organizations?:Organization[];
  practitioners?:Practitioner[];
  statuses?:ExamStatus[];
  priorities?:ExamPriority[];
  patients?:Patient[];
  serviceTypes?:ServiceType[];
  examinationForm?:FormGroup;

  constructor(private router:Router,private http: HttpExaminationService,private fb:FormBuilder) { }

  ngOnInit(): void {
    this.http.getAllExamPriority().pipe(take(1)).subscribe(priorites=>this.priorities=priorites);
    this.http.getAllExamStatus().pipe(take(1)).subscribe(statuses=>this.statuses=statuses);
    this.http.getAllServiceType().pipe(take(1)).subscribe(serviceTypes=>this.serviceTypes=serviceTypes);
    this.http.getAllPatients().pipe(take(1)).subscribe(patients=>this.patients=patients);
    this.http.getAllOrganizations().pipe(take(1)).subscribe(organizations=>{this.organizations=organizations;this.createForm();});
  }
  organizationSelected(event:Event){
    this.examinationForm?.controls['practitioners'].setValue([]);
    this.http.getAllPractitioners(this.examinationForm?.controls['organization'].value.id).pipe(take(1)).subscribe(practitioners=>{
      this.practitioners=practitioners;
      console.log("pract",this.practitioners)
      this.isOrganizationSelected=true;
      this.dropdownList=practitioners;
      console.log("dropdownlist",this.dropdownList);
    })
  }
  createForm(){
    this.examinationForm=this.fb.group({
      id:[-1],
      serviceTypeDto:[this.examination?.serviceTypeDto,Validators.required],
      startDate:[this.examination?.startDate],
      endDate:[this.examination?.endDate],
      diagnosis:[this.examination?.diagnosis],
      patient:[null,Validators.required],
      practitioners:[null],
      organization:[null,Validators.required],
      priority:[this.examination?.priority],
      status:[this.examination?.status,Validators.required]
    })
  }
  save(){
    if(this.examinationForm?.controls['startDate'].value){
      let ngbDate= this.examinationForm?.controls['startDate'].value;
      let myDate=new Date(ngbDate.year,ngbDate.month-1,ngbDate.day);
      let formValues=this.examinationForm?.value;
      formValues['startDate']=myDate;
      this.examination=formValues;}
    if(this.examinationForm?.controls['endDate'].value){
        let ngbDate= this.examinationForm?.controls['endDate'].value;
        let myDate=new Date(ngbDate.year,ngbDate.month-1,ngbDate.day);
        let formValues=this.examinationForm?.value;
        formValues['endDate']=myDate;
        this.examination=formValues;}
    this.examination=this.examinationForm?.value;
    this.http.save(this.examination!).subscribe(response=>{this.router.navigate(['examination/examination-list'])})
  }
  hasErrors(componentName: string, errorCode?: string) {
    return  (this.examinationForm?.get(componentName)?.dirty || this.examinationForm?.get(componentName)?.touched  ) &&
    ((!errorCode && this.examinationForm?.get(componentName)?.errors ) ||
    (errorCode && this.examinationForm?.get(componentName)?.hasError(errorCode)));
  }
  onCancel(){
    this.router.navigate(['examination/examination-list'])
  }
}
