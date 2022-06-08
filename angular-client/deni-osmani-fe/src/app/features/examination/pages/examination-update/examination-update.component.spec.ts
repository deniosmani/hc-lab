import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationUpdateComponent } from './examination-update.component';

describe('ExaminationUpdateComponent', () => {
  let component: ExaminationUpdateComponent;
  let fixture: ComponentFixture<ExaminationUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
