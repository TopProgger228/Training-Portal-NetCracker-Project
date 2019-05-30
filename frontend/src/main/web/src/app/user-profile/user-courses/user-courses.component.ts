import { Component, OnInit } from '@angular/core';

import { Router} from '@angular/router';
import { TokenStorageService } from '../../auth/token-storage.service';

import { CourseService } from "../../services/course.service";
import {UserService} from "../../services/user.service";
import {CourseWithTrainer} from "../../interface/courseWithTrainer";


@Component({
  selector: 'app-user-courses',
  templateUrl: './user-courses.component.html',
  styleUrls: ['./user-courses.component.css']
})
export class UserCoursesComponent implements OnInit {

  courses: CourseWithTrainer[];
  loggedOut = false;

  constructor(private router: Router, private courseService: CourseService, private userService: UserService, private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if ('Student' === role) {
      this.courseService.getStudentCoursesByUsername(this.token.getUsername())
      .subscribe(data => {
      console.log(data.body);
      this.courses = data.body;
    })

} else {

}
        return true;
      });
    } else {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    }
  };

  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}



