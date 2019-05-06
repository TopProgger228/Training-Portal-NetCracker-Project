import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStudentMailSenderComponent } from './add-student';

describe('AddUserComponent', () => {
  let component: AddStudentMailSenderComponent;
  let fixture: ComponentFixture<AddStudentMailSenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddStudentMailSenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStudentMailSenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
