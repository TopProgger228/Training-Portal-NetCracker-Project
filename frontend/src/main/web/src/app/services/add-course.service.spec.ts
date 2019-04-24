import { TestBed } from '@angular/core/testing';

import { AddCourseService } from './add-course.service';

describe('AddCourseService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddCourseService = TestBed.get(AddCourseService);
    expect(service).toBeTruthy();
  });
});
