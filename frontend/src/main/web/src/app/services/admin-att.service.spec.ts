import { TestBed } from '@angular/core/testing';

import { AdminAttService } from './admin-att.service';

describe('AdminAttService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdminAttService = TestBed.get(AdminAttService);
    expect(service).toBeTruthy();
  });
});
