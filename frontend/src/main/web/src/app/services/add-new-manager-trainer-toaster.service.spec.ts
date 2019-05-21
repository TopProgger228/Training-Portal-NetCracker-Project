import { TestBed } from '@angular/core/testing';

import { AddNewManagerTrainerToasterService } from './add-new-manager-trainer-toaster.service';

describe('AddNewManagerTrainerToasterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddNewManagerTrainerToasterService = TestBed.get(AddNewManagerTrainerToasterService);
    expect(service).toBeTruthy();
  });
});
