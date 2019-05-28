import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerLessonsComponent } from './trainer-lessons.component';

describe('TrainerLessonsComponent', () => {
  let component: TrainerLessonsComponent;
  let fixture: ComponentFixture<TrainerLessonsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerLessonsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerLessonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
