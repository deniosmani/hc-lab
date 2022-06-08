import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Practitioner } from 'src/app/core/models/practitioner.model';
import { HttpPractitionerService } from 'src/app/core/services/http-practitioner.service';

@Component({
  selector: 'app-practitioner-details',
  templateUrl: './practitioner-details.component.html',
  styleUrls: ['./practitioner-details.component.css']
})
export class PractitionerDetailsComponent implements OnInit {

  practitioner?:Practitioner

  constructor(private activeRoute:ActivatedRoute, private router:Router,private http:HttpPractitionerService) { }

  ngOnInit(): void {
    const id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    this.http.findById(id).subscribe((practitioner=>this.practitioner=practitioner));
  }

}
