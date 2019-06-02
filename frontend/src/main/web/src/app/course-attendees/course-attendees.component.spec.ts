import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseAttendeesComponent } from './course-attendees.component';

describe('CourseAttendeesComponent', () => {
  let component: CourseAttendeesComponent;
  let fixture: ComponentFixture<CourseAttendeesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseAttendeesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseAttendeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
