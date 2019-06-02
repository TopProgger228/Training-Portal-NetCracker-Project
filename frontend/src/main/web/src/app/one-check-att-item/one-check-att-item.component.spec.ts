import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OneCheckAttItemComponent } from './one-check-att-item.component';

describe('OneCheckAttItemComponent', () => {
  let component: OneCheckAttItemComponent;
  let fixture: ComponentFixture<OneCheckAttItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OneCheckAttItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OneCheckAttItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
