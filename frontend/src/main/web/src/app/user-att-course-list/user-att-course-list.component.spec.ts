import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAttCourseListComponent } from './user-att-course-list.component';

describe('UserAttCourseListComponent', () => {
  let component: UserAttCourseListComponent;
  let fixture: ComponentFixture<UserAttCourseListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserAttCourseListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAttCourseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
