import { Component, OnInit } from '@angular/core';
import { COURSES } from '../default-courses';
import { StockCourses } from "../stock-courses";
import { CourseService } from '../../../services/course.service';
import { Course } from '../../../services/course'


@Component({
  selector: 'app-courses-container',
  templateUrl: './courses-container.component.html',
  styleUrls: ['./courses-container.component.css']
})
export class CoursesContainerComponent implements OnInit {

  courses: Course[];
  coursesC: Array<StockCourses> = COURSES;
  constructor(private courseService: CourseService) { }

  ngOnInit() {
    this.courseService.getLastTenCoursesList().subscribe(data => {
      this.courses = data;
      console.log(data);
    })
  }

}
