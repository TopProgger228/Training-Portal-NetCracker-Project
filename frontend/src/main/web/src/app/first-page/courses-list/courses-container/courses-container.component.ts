import { Component, OnInit } from '@angular/core';
import { COURSES } from '../default-courses';
import { StockCourses } from "../stock-courses";
import { CourseService } from '../../../services/course.service';
import { Course } from '../../../services/course'

import {TokenStorageService} from "../../../auth/token-storage.service";
import {CourseWithChoosen} from "../../../services/course-with-choosen";


@Component({
  selector: 'app-courses-container',
  templateUrl: './courses-container.component.html',
  styleUrls: ['./courses-container.component.css']
})
export class CoursesContainerComponent implements OnInit {

  courses: CourseWithChoosen[];
  coursesC: Array<StockCourses> = COURSES;

  constructor(private courseService: CourseService,
              private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.courseService.getActiveCoursesList().subscribe(data => {
      this.courses = data;
      console.log(data);
      console.log(this.tokenStorage);
    })
  }

}
