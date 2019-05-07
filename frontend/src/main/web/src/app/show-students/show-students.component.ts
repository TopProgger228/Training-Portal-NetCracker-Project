import {Component, OnInit} from '@angular/core';
import {Student} from "../services/student";
import {Router} from "@angular/router";
import {StudentService} from "../services/student.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {Manager} from "../services/manager";
import {ManagerService} from "../services/manager.service";

@Component({
  selector: 'app-show-students',
  templateUrl: './show-students.component.html',
  styleUrls: ['./show-students.component.css']
})
export class ShowStudentsComponent implements OnInit {
  students: Student[];
  managers: Manager[];
  loggedOut = false;

  constructor(private router: Router, private studentService: StudentService, private managerService: ManagerService,
              private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.studentService.getStudents(this.token.getUsername())
        .subscribe(data => {
          this.students = data;
        })
      this.managerService.getManagerOfStudent(this.token.getUsername())
        .subscribe(data => {
          this.managers = data;
        })
    } else {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    }
    ;
  };

  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  };

  onGetClick(id: number) {
    this.studentService.getStudentById(id);
    this.router.navigate(['manager/profile']);
  }


}
