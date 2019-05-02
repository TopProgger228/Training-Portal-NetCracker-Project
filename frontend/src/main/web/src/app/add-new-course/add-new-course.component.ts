import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TrainerService} from "../services/trainer.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {Course} from "../services/course";
import {AddCourseService} from "../services/add-course.service";
import {Trainer} from "../services/trainer";

@Component({
  selector: 'app-add-new-course',
  templateUrl: './add-new-course.component.html',
  styleUrls: ['./add-new-course.component.css']
})

export class AddNewCourseComponent implements OnInit{
  loggedout = false;
  //course = new Course("", "", "", "", "",0,0);
  course = new Course(null,null,null,null,null,null,null);
  levels = ['Junior', 'Middle', 'Senior'];
  trainers: Trainer[];

  constructor(private router: Router, private trainerService: TrainerService, private token: TokenStorageService, private httpService : AddCourseService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.trainerService.getTrainers()
        .subscribe(data => {
          this.trainers = data;
        })
    } else {
      this.loggedout = true;
      this.router.navigate(['auth/login']);
    };
  };

  submitted = false;

  onSubmit(){
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

