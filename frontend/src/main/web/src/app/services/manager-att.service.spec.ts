import { TestBed } from '@angular/core/testing';

import { ManagerAttService } from './manager-att.service';

describe('ManagerAttService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ManagerAttService = TestBed.get(ManagerAttService);
    expect(service).toBeTruthy();
  });
});
