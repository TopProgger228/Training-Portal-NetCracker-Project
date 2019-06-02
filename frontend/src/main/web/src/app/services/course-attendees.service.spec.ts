import { TestBed } from '@angular/core/testing';

import { CourseAttendeesService } from './course-attendees.service';

describe('CourseAttendeesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CourseAttendeesService = TestBed.get(CourseAttendeesService);
    expect(service).toBeTruthy();
  });
});
