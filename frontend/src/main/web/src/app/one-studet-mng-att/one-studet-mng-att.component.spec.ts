import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OneStudetMngAttComponent } from './one-studet-mng-att.component';

describe('OneStudetMngAttComponent', () => {
  let component: OneStudetMngAttComponent;
  let fixture: ComponentFixture<OneStudetMngAttComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OneStudetMngAttComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OneStudetMngAttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
