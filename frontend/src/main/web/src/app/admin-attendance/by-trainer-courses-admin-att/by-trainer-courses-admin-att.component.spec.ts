import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ByTrainerCoursesAdminAttComponent } from './by-trainer-courses-admin-att.component';

describe('ByTrainerCoursesAdminAttComponent', () => {
  let component: ByTrainerCoursesAdminAttComponent;
  let fixture: ComponentFixture<ByTrainerCoursesAdminAttComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ByTrainerCoursesAdminAttComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ByTrainerCoursesAdminAttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
