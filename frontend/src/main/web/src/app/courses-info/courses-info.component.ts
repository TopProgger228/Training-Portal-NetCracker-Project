import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';

import {Course} from "../services/course";
import {CourseService} from "../services/course.service";


@Component({
  selector: 'app-users-info',
  templateUrl: './courses-info.html',
  styleUrls: ['./courses-info.css']
})
export class CoursesInfoComponent implements OnInit {

  courses: Course[];

  currentJustify = 'start';

  loggedOut = false;

  constructor(private router: Router, private courseService: CourseService, private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.courseService.getCourses(this.token.getUsername())
        .subscribe(data => {
          this.courses = data;
        })
    } else {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    };
  };

  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }


}



