import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerLessonAttendanceComponent } from './trainer-lesson-attendance.component';

describe('TrainerLessonAttendanceComponent', () => {
  let component: TrainerLessonAttendanceComponent;
  let fixture: ComponentFixture<TrainerLessonAttendanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerLessonAttendanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerLessonAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
