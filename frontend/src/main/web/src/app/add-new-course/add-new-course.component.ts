import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { TrainerService } from "../services/trainer.service";
import { TokenStorageService } from "../auth/token-storage.service";
import { Course } from "../interface/course";
import { AddCourseService } from "../services/add-course.service";
import { Trainer } from "../interface/trainer";

@Component({
  selector: 'app-add-new-course',
  templateUrl: './add-new-course.component.html',
  styleUrls: ['./add-new-course.component.css']
})

export class AddNewCourseComponent implements OnInit {
  loggedout = false;
  //course = new Course("", "", "", "", "",0,0);
  levels = ['Junior', 'Middle', 'Senior'];
  trainers: Trainer[];
  course = new Course("", "", "", "", "", 0, 1);


  constructor(private router: Router, private trainerService: TrainerService, private token: TokenStorageService, private httpService: AddCourseService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Admin') {
          this.trainerService.getTrainers()
            .subscribe(data => {
              this.trainers = data;
            })
        } else {
          this.router.navigate(['firstPage']);
        }
        return false;
      });
    } else {
      this.loggedout = true;
      this.router.navigate(['auth/login']);
    };
  };

  submitted = false;

  onSubmit() {
    console.log(this.course);
    this.httpService.addCourse(this.course).subscribe(
      value => {
        console.log('[POST] create course successfully', value);
        this.router.navigate(['auth/login']);
      }, error => {
        console.log('FAIL to create course!');
      },
      () => {
        console.log('POST course - now completed.');
      });
  }
}
