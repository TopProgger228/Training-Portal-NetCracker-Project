import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCoursesAdminAttComponent } from './all-courses-admin-att.component';

describe('AllCoursesAdminAttComponent', () => {
  let component: AllCoursesAdminAttComponent;
  let fixture: ComponentFixture<AllCoursesAdminAttComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllCoursesAdminAttComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllCoursesAdminAttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
