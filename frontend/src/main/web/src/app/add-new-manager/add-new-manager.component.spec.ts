import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewManagerComponent } from './add-new-manager.component';

describe('AddNewManagerComponent', () => {
  let component: AddNewManagerComponent;
  let fixture: ComponentFixture<AddNewManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
