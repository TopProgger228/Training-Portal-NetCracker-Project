import { Component, OnInit } from '@angular/core';
import {Course} from "../services/course";
import {Router} from "@angular/router";
import {CourseService} from "../services/course.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {StudentService} from "../services/student.service";

@Component({
  selector: 'app-students-info',
  templateUrl: './students-info.component.html',
  styleUrls: ['./students-info.component.css']
})
export class StudentsInfoComponent implements OnInit {

  courses: Course[];

  loggedOut = false;

  constructor(private router: Router, private studentService: StudentService, private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.studentService.getStudents(this.token.getUsername())
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
