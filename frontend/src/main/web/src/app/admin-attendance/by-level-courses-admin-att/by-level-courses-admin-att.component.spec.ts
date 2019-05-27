import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ByLevelCoursesAdminAttComponent } from './by-level-courses-admin-att.component';

describe('ByLevelCoursesAdminAttComponent', () => {
  let component: ByLevelCoursesAdminAttComponent;
  let fixture: ComponentFixture<ByLevelCoursesAdminAttComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ByLevelCoursesAdminAttComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ByLevelCoursesAdminAttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
