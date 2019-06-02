import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../../services/course.service';
import {CourseWithChoosen} from "../../../services/course-with-choosen";


@Component({
  selector: 'app-courses-container',
  templateUrl: './courses-container.component.html',
  styleUrls: ['./courses-container.component.css']
})
export class CoursesContainerComponent implements OnInit {

  courses: CourseWithChoosen[];

  constructor(private courseService: CourseService) { }

  ngOnInit() {
    this.courseService.getActiveCoursesList().subscribe(data => {
      this.courses = data;
    })
  }

}
