import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PractitionerUpdateComponent } from './practitioner-update.component';

describe('PractitionerUpdateComponent', () => {
  let component: PractitionerUpdateComponent;
  let fixture: ComponentFixture<PractitionerUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PractitionerUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PractitionerUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
