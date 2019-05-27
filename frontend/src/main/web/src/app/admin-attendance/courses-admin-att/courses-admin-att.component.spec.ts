import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesAdminAttComponent } from './courses-admin-att.component';

describe('CoursesAdminAttComponent', () => {
  let component: CoursesAdminAttComponent;
  let fixture: ComponentFixture<CoursesAdminAttComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoursesAdminAttComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoursesAdminAttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
