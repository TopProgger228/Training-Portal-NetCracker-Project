import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OneTrainerCourseComponent } from './one-trainer-course.component';

describe('OneTrainerCourseComponent', () => {
  let component: OneTrainerCourseComponent;
  let fixture: ComponentFixture<OneTrainerCourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OneTrainerCourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OneTrainerCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
