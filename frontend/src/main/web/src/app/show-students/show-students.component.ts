import { Component, OnInit } from '@angular/core';
import {Student} from "../services/student";
import {Router} from "@angular/router";
import {StudentService} from "../services/student.service";
import {TokenStorageService} from "../auth/token-storage.service";

@Component({
  selector: 'app-show-students',
  templateUrl: './show-students.component.html',
  styleUrls: ['./show-students.component.css']
})
export class ShowStudentsComponent implements OnInit {
  students : Student[];

  loggedOut = false;

  constructor(private router : Router, private studentService : StudentService,
              private token : TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.studentService.getStudents(this.token.getUsername())
        .subscribe(data => {
          this.students = data;
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
