import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Organization } from 'src/app/core/models/organization.model';
import { HttpOrganizationService } from 'src/app/core/services/http-organization.service';

@Component({
  selector: 'app-organization-details',
  templateUrl: './organization-details.component.html',
  styleUrls: ['./organization-details.component.css']
})
export class OrganizationDetailsComponent implements OnInit {

  organization?:Organization;
  numberOfPract?:number;
  constructor(private activeRoute:ActivatedRoute, private router: Router, private http:HttpOrganizationService) { }

  ngOnInit(): void {
    const id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    this.http.findById(id).subscribe((organization=>{this.organization=organization;this.http.getPractitoners(id).subscribe(response=>this.numberOfPract=response)}));
  }

}
