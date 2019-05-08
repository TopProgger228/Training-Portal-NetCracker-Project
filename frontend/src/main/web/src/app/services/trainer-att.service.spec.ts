import { TestBed } from '@angular/core/testing';

import { TrainerAttService } from './trainer-att.service';

describe('TrainerAttService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainerAttService = TestBed.get(TrainerAttService);
    expect(service).toBeTruthy();
  });
});
