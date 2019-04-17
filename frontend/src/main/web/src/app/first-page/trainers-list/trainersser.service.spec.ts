import { TestBed } from '@angular/core/testing';

import { TrainersserService } from './trainersser.service';

describe('TrainersserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainersserService = TestBed.get(TrainersserService);
    expect(service).toBeTruthy();
  });
});
