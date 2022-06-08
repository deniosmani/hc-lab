import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { take } from 'rxjs';
import {
  Examination,
  ExamPriority,
  ExamStatus,
  ServiceType,
} from 'src/app/core/models/examination.model';
import { Organization } from 'src/app/core/models/organization.model';
import { Patient } from 'src/app/core/models/patient.model';
import { Practitioner } from 'src/app/core/models/practitioner.model';
import { HttpExaminationService } from 'src/app/core/services/http-examination.service';

@Component({
  selector: 'app-examination-update',
  templateUrl: './examination-update.component.html',
  styleUrls: ['./examination-update.component.css'],
})
export class ExaminationUpdateComponent implements OnInit {
  dropdownList = [];
  examination?: Examination;
  organizations?: Organization[];
  practitioners?: Practitioner[];
  dropdownSettings: IDropdownSettings = {
    selectAllText: 'Select All',
    unSelectAllText: 'UnSelect All',
    singleSelection: false,
    idField: 'id',
    textField: 'name',
    noDataAvailablePlaceholderText:
      'There is no practitioner in this organization',
  };
  statuses?: ExamStatus[];
  priorities?: ExamPriority[];
  patients?: Patient[];
  serviceTypes?: ServiceType[];
  examinationForm?: FormGroup;

  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private http: HttpExaminationService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    const id = Number(this.activeRoute.snapshot.paramMap.get('id'));
    this.http
      .getAllExamPriority()
      .pipe(take(1))
      .subscribe((priorites) => (this.priorities = priorites));
    this.http
      .getAllExamStatus()
      .pipe(take(1))
      .subscribe((statuses) => (this.statuses = statuses));
    this.http
      .getAllServiceType()
      .pipe(take(1))
      .subscribe((serviceTypes) => (this.serviceTypes = serviceTypes));
    this.http
      .getAllPatients()
      .pipe(take(1))
      .subscribe((patients) => (this.patients = patients));
    this.http
      .getAllOrganizations()
      .pipe(take(1))
      .subscribe((organizations) => {
        this.organizations = organizations;
      });
    this.http.findById(id).subscribe((examination) => {
      this.examination = examination;
      this.http
        .getAllPractitioners(this.examination?.organization?.id!)
        .subscribe((practitioners) => {
          this.practitioners = practitioners;
          this.dropdownList = practitioners;
        });
      this.createForm();
    });
  }

  createForm() {
    this.examinationForm = this.fb.group({
      id: [this.examination?.id],
      serviceTypeDto: [
        this.examination?.serviceTypeDto?.value,
        Validators.required,
      ],
      startDate: [this.examination?.startDate],
      endDate: [this.examination?.endDate],
      patient: [this.examination?.patient],
      diagnosis: [this.examination?.diagnosis],
      practitioners: [this.examination?.practitioners],
      organization: [this.examination?.organization, Validators.required],
      priority: [this.examination?.priority?.value],
      status: [this.examination?.status?.value, Validators.required],
    });
  }
  update() {
    this.examination = this.examinationForm?.value;
    this.http.update(this.examination!).subscribe((response) => {
      this.router.navigate(['examination/examination-list']);
    });
  }
  hasErrors(componentName: string, errorCode?: string) {
    return (
      (this.examinationForm?.get(componentName)?.dirty ||
        this.examinationForm?.get(componentName)?.touched ||
        this.examinationForm?.get(componentName)?.invalid) &&
      ((!errorCode && this.examinationForm?.get(componentName)?.errors) ||
        (errorCode &&
          this.examinationForm?.get(componentName)?.hasError(errorCode)))
    );
  }
  compareByOrgID(item1: Organization, item2: Organization) {
    return item1 && item2 && item1.id == item2.id;
  }
  organizationSelected(event: Event) {
    if (
      this.examinationForm?.controls['organization'].value.id ==
      this.examination?.organization.id
    ) {
      console.log('uso');
      this.examinationForm?.controls['practitioners'].setValue(
        this.examination?.practitioners
      );
    } else this.examinationForm?.controls['practitioners'].setValue([]);
    this.http
      .getAllPractitioners(
        this.examinationForm?.controls['organization'].value.id
      )
      .pipe(take(1))
      .subscribe((practitioners) => {
        this.practitioners = practitioners;
        console.log('pract', this.practitioners);
        this.dropdownList = practitioners;
        console.log('dropdownlist', this.dropdownList);
      });
  }
  onCancel(){
    this.router.navigate(['examination/examination-list'])
  }
}
