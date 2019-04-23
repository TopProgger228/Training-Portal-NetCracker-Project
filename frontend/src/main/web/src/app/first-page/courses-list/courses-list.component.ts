import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorageService} from "../../auth/token-storage.service";
import {Course} from "./course";
import {CourseService} from "./course.service";

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit {

  form: any = {};
  course: Course;

  roles: string[] = [];
  errorMessage = '';

  constructor(private courseService: CourseService,
              private router: Router,
              private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {

    }
  }

  onSubmit() {
    console.log(this.form);

    this.course = new Course(
      this.form.name,
      this.form.info,
      this.form.trainer,
      this.form.skill_level,
      this.form.learn_direction);

    this.courseService.createNewCourse(this.course).subscribe(
      data => {
        console.log(data);

      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }

}
