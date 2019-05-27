import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ByStudentCoursesAdminAttComponent } from './by-student-courses-admin-att.component';

describe('ByStudentCoursesAdminAttComponent', () => {
  let component: ByStudentCoursesAdminAttComponent;
  let fixture: ComponentFixture<ByStudentCoursesAdminAttComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ByStudentCoursesAdminAttComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ByStudentCoursesAdminAttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
