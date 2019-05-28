import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OneTrainerLessonComponent } from './one-trainer-lesson.component';

describe('OneTrainerLessonComponent', () => {
  let component: OneTrainerLessonComponent;
  let fixture: ComponentFixture<OneTrainerLessonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OneTrainerLessonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OneTrainerLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
