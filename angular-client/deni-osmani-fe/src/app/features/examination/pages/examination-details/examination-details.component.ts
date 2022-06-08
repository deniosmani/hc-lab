import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Examination } from 'src/app/core/models/examination.model';
import { HttpExaminationService } from 'src/app/core/services/http-examination.service';


@Component({
  selector: 'app-examination-details',
  templateUrl: './examination-details.component.html',
  styleUrls: ['./examination-details.component.css']
})
export class ExaminationDetailsComponent implements OnInit {

  examination?:Examination;

  constructor(private activeRoute:ActivatedRoute, private router: Router, private http:HttpExaminationService) { }

  ngOnInit(): void {
    const id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    this.http.findById(id).subscribe((examination=>this.examination=examination));
  }

}
