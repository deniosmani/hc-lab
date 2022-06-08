import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from 'src/app/core/models/patient.model';
import { HttpPatientService } from 'src/app/core/services/http-patient.service';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {

  patient?:Patient;

  constructor(private activeRoute:ActivatedRoute, private router: Router, private http:HttpPatientService) { }

  ngOnInit(): void {
    const id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    this.http.findById(id).subscribe((patient=>this.patient=patient));
  }

}
