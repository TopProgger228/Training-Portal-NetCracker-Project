import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LessonAttComponent } from './lesson-att.component';

describe('LessonAttComponent', () => {
  let component: LessonAttComponent;
  let fixture: ComponentFixture<LessonAttComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LessonAttComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LessonAttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
