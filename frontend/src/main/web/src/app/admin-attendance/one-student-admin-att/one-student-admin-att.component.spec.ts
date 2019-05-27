import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OneStudentAdminAttComponent } from './one-student-admin-att.component';

describe('OneStudentAdminAttComponent', () => {
  let component: OneStudentAdminAttComponent;
  let fixture: ComponentFixture<OneStudentAdminAttComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OneStudentAdminAttComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OneStudentAdminAttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
