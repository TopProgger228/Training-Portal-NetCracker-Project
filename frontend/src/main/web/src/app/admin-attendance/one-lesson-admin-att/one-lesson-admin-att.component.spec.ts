import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OneLessonAdminAttComponent } from './one-lesson-admin-att.component';

describe('OneLessonAdminAttComponent', () => {
  let component: OneLessonAdminAttComponent;
  let fixture: ComponentFixture<OneLessonAdminAttComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OneLessonAdminAttComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OneLessonAdminAttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
