import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupsScheduleComponent } from './groups-schedule.component';

describe('GroupsScheduleComponent', () => {
  let component: GroupsScheduleComponent;
  let fixture: ComponentFixture<GroupsScheduleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupsScheduleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupsScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
