import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';

import { Course } from "../services/course";
import { CourseService } from "../services/course.service";


@Component({
  selector: 'app-users-info',
  templateUrl: './courses-info.html',
  styleUrls: ['./courses-info.css']
})
export class CoursesInfoComponent implements OnInit {

  courses: Course[];

  loggedOut = false;

  constructor(private router: Router, private courseService: CourseService, private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Trainer') {
          this.courseService.getCourses(this.token.getUsername())
            .subscribe(data => {
              this.courses = data;
            })
        } else {
          this.router.navigate(['firstPage']);
        }
        return false;
      });
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



