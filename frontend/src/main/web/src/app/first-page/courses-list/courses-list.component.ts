import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorageService} from "../../auth/token-storage.service";
import {CoursesService} from "./courses.service";
import {Courses} from "./courses";

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit {

  courses: Courses[];

  loggedout = false;

  constructor(private router: Router, private coursesService: CoursesService, private token: TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.coursesService.getCourses()
        .subscribe(data => {
          this.courses = data;
        })
    }else {
      this.loggedout = true;
      this.router.navigate(['auth/login']);
    };
  };

  logout() {
    this.loggedout = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}
