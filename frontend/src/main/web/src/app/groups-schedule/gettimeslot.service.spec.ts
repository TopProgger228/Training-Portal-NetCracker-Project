import { TestBed } from '@angular/core/testing';

import { GettimeslotService } from './gettimeslot.service';

describe('GettimeslotService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GettimeslotService = TestBed.get(GettimeslotService);
    expect(service).toBeTruthy();
  });
});
