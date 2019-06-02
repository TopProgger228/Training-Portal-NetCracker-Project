import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerAttendanceComponent } from './trainer-attendance.component';

describe('TrainerAttendanceComponent', () => {
  let component: TrainerAttendanceComponent;
  let fixture: ComponentFixture<TrainerAttendanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerAttendanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerAttendanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
