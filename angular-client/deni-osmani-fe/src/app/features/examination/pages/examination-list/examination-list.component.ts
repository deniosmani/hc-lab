import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Examination } from 'src/app/core/models/examination.model';
import { Organization } from 'src/app/core/models/organization.model';
import { HttpExaminationService } from 'src/app/core/services/http-examination.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header.directive';

@Component({
  selector: 'app-examination-list',
  templateUrl: './examination-list.component.html',
  styleUrls: ['./examination-list.component.css']
})
export class ExaminationListComponent implements OnInit {

  examinations?:Examination[];
  availablePageSize = [2, 5, 10, 15, 20];
  currentPage=1;
  totalItems=10;
  pageSize=5;
  sortOrder='asc';
  sortBy='diagnosis';
  orgId=-1;
  patient="";
  practitioner='';
  filterForm?:FormGroup;
  organizations?:Organization[];
  @ViewChildren(SortableHeaderDirective)
  headers?:QueryList<SortableHeaderDirective>
  constructor(private httpExamination:HttpExaminationService,private router:Router,private fb:FormBuilder) { }
  onPageSizeChange(){
    this.loadExaminations();
  }
  ngOnInit(): void {
   this.loadExaminations();
   console.log("pageSize",this.pageSize)
   this.httpExamination.getAllOrganizations().pipe(take(1)).subscribe(organizations=>{this.organizations=organizations;this.createForm()});
  }
  loadExaminations(){
    this.httpExamination.getByPage(this.practitioner,this.patient,this.orgId,this.currentPage,this.pageSize,this.sortBy,this.sortOrder).subscribe(
      patientPage => {
        this.examinations=patientPage.content;
        this.totalItems=patientPage.totalElements;
        if(patientPage.size>0)
        this.pageSize=patientPage.size;
      }
    )
  }
  onPageChange(page: number) {
    this.loadExaminations();
  }
  addExamination(){
    this.router.navigate(['examination/examination-add']);
  }
  onDelete(examination:Examination){
    if(confirm("Are you sure?")){
    this.httpExamination.deleteExamination(examination).subscribe((examination=>{this.loadExaminations();}));}
  }
  onDetails(examination:Examination){
    this.router.navigate(['examination/examination-details/',examination.id]);
  }
  onEdit(examination:Examination){
    this.router.navigate(['examination/examination-update',examination.id]);
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
    this.loadExaminations();
  }
  createForm(){
    this.filterForm=this.fb.group({
      orgId:[this.orgId],
      patient:[this.patient],
      practitioner:[this.practitioner]
    })
  }
  onSubmitForm(){

    if(this.filterForm?.controls['orgId'].touched){
      if(typeof this.filterForm?.controls['orgId'].value ==="string"){
        this.orgId=-1;
      } else
        this.orgId=this.filterForm?.controls['orgId']?.value?.id;
    }
    this.patient=this.filterForm?.controls['patient']?.value;
    this.practitioner=this.filterForm?.controls['practitioner']?.value;
    this.loadExaminations();
  }
}
