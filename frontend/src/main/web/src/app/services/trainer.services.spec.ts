import { TestBed, inject } from '@angular/core/testing';

import { TrainerService } from './trainer.service';

describe('UserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TrainerService]
    });
  });

  it('should be created', inject([TrainerService], (service: TrainerService) => {
    expect(service).toBeTruthy();
  }));
});
