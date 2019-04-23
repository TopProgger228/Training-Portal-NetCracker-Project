import { Component, OnInit } from '@angular/core';
import { COURSES } from '../default-courses';
import { Course } from '../course';


@Component({
  selector: 'app-courses-container',
  templateUrl: './courses-container.component.html',
  styleUrls: ['./courses-container.component.css']
})
export class CoursesContainerComponent implements OnInit {

  coursesC: Array<Course> = COURSES;
  constructor() { }

  ngOnInit() {
  }

}
