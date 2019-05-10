import { Component, OnInit } from '@angular/core';
import {AdminAttService} from "../services/admin-att.service";
import {UserAtt} from "../interface/user-att";
import {LessonAtt} from "../interface/lesson-att";
import {CourseAtt} from "../interface/course-att";
import {TrainerSelector} from "../interface/trainer-selector";

@Component({
  selector: 'app-admin-attendance',
  templateUrl: './admin-attendance.component.html',
  styleUrls: ['./admin-attendance.component.css']
})
export class AdminAttendanceComponent implements OnInit {
  panelOpenState = false;

  courseList: CourseAtt[];
  courseListByTrainer: CourseAtt[];
  courseListByUser: CourseAtt[];
  courseListByLevel: CourseAtt[];
  lessonList: LessonAtt[];
  userList: UserAtt[];
  choosenOne: number = -1;
  choosenOneLesson: number = -1;
  isHidden: boolean[] = [];

  trainerSelector: TrainerSelector[];
  trainerSelected: number;

  levelSelector: string[];
  levelSelected: number;

  userValue: string;


  constructor(private adminService: AdminAttService) { }

  setChoosen (id: number ){
    this.choosenOne = id;
    this.getLessons(id);
    this.isHiddenMethod(id);
  }

  setChoosenLesson (id:number) {
    this.choosenOneLesson = id;
  }

  ngOnInit() {

    this.adminService.getCourses()
      .subscribe(data => this.courseList = data);


    this.levelSelector = [
      "Beginner", "Junior", "Middle", "Master"
    ]

    this.adminService.getAllTrainer()
      .subscribe(data=>this.trainerSelector = data);

    this.trainerSelected = -1;
    this.levelSelected = -1;
  }

  getLessons (id: number) {
    this.adminService.getLessons(id)
      .subscribe(data => this.lessonList = data);
  }

  getUsers (id: number) {
    this.adminService.getStudents(id)
      .subscribe(data => this.userList = data);
  }

  isHiddenMethod (id: number) {
    if(this.isHidden[id] == null) {
      for (let i of this.isHidden) {
        i = true;
      }
      this.isHidden[id] = false;

    } else {
      this.isHidden[id] = !this.isHidden[id];
    }
  }


  filterByUser(username: string) {
    this.adminService.filterByUser(username)
      .subscribe(data => this.courseListByUser = data);
  }

  filterByTrainer(id: number) {
    this.adminService.filterByTrainer(id)
      .subscribe(data => this.courseListByTrainer = data);
  }

  filterBySkillLevel(level: string) {
    this.adminService.filterBySkillLevel(level)
      .subscribe(data => this.courseListByLevel = data);
  }

  onTrainerSelected (val: any) {
    this.filterByTrainer(val);
    console.log(val)
  }

  onLevelSelected (val: any) {
    this.filterBySkillLevel(val);
    console.log(val);
  }

}
