import { HttpErrorResponse } from '@angular/common/http';
import { identifierName } from '@angular/compiler';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Organization } from 'src/app/core/models/organization.model';
import { HttpOrganizationService } from 'src/app/core/services/http-organization.service';
import { SortableHeaderDirective, SortEvent } from 'src/app/shared/directives/sortable-header.directive';

@Component({
  selector: 'app-organization-list',
  templateUrl: './organization-list.component.html',
  styleUrls: ['./organization-list.component.css']
})
export class OrganizationListComponent implements OnInit {
  availablePageSize = [2, 5, 10, 15, 20];
  organizations?:Organization[];
  currentPage=1;
  totalItems=10;
  pageSize=5;
  sortBy='name';
  sortOrder='asc';
  search='';
  names?:string[];
  searchForm?:FormGroup;

  @ViewChildren(SortableHeaderDirective)
  headers?:QueryList<SortableHeaderDirective>

  constructor(private httpOrganization:HttpOrganizationService,private router:Router, private fb:FormBuilder) { }
  onPageSizeChange(){
    this.loadOrganizations();
  }
  ngOnInit(): void {
    this.loadOrganizations();
    this.createForm();
    this.httpOrganization.getNames().pipe(take(1)).subscribe(names=>{this.names=names;console.log(this.names);});
  }

  onEdit(organization:Organization){
    this.router.navigate(['organization/organization-update',organization.id]);
  }
  onDetails(organization:Organization){

    this.router.navigate(['organization/organization-details/',organization.id]);

  }
  onDelete(organization:Organization){
    if(confirm("Are you sure?")){
    this.httpOrganization.deleteOrganization(organization).subscribe((organization=>{this.loadOrganizations();}),(error:HttpErrorResponse)=>{alert(error.error)});}
  }
  addOrganization(){
    this.router.navigate(['organization/organization-add']);
  }
  onPageChange(page: number) {
    this.loadOrganizations();
  }
  loadOrganizations(){
    this.httpOrganization.getByPage(this.search,this.currentPage,this.pageSize,this.sortBy,this.sortOrder).subscribe(
      organizationPage => {
        this.organizations=organizationPage.content;
        this.totalItems=organizationPage.totalElements;
        this.pageSize=organizationPage.size;
      }
    )
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
    this.loadOrganizations();
  }
  filter(){
    this.search=this.searchForm?.controls['search'].value;
    this.loadOrganizations();
  }
  createForm(){
    this.searchForm = this.fb.group({
      search:[this.search]
    })
  }
}
