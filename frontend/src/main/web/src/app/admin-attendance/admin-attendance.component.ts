import { Component, OnInit } from '@angular/core';
import { AdminAttService } from "../services/admin-att.service";
import { UserAtt } from "../interface/user-att";
import { LessonAtt } from "../interface/lesson-att";
import { CourseAtt } from "../interface/course-att";
import { TrainerSelector } from "../interface/trainer-selector";

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

  isChooseCoursesSelected: boolean;
  isSelectAllSelected: boolean;

  trainerSelector: TrainerSelector[];
  trainerSelected: number;

  levelSelector: string[];
  levelSelected: number;

  userValue: string;

  selectedCourseArray: Array<{ id: any, isSelected: any }> = [];
  selectedCoursesForReport: Array<number> = [];

  constructor(private adminService: AdminAttService) { }

  setChoosen(id: number) {
    this.choosenOne = id;
    this.getLessons(id);
    this.isHiddenMethod(id);
  }

  setChoosenLesson(id: number) {
    this.choosenOneLesson = id;
  }

  ngOnInit() {

    this.isChooseCoursesSelected = false;
    this.isSelectAllSelected = true;

    this.adminService.getCourses()
      .subscribe(data => this.courseList = data);
    this.levelSelector = [
      "Beginner", "Junior", "Middle", "Master"
    ]
    this.adminService.getAllTrainer()
      .subscribe(data => this.trainerSelector = data);

    this.trainerSelected = -1;
    this.levelSelected = -1;
  }


  setSelectedCoursesArray() {
    this.selectedCourseArray = [];
    this.courseList.forEach(course => {
      this.selectedCourseArray.push({ id: course.courseId, isSelected: true });
    });
  }
  getLessons(id: number) {
    this.adminService.getLessons(id)
      .subscribe(data => this.lessonList = data);
  }

  getUsers(id: number) {
    this.adminService.getStudents(id)
      .subscribe(data => this.userList = data);
  }

  isHiddenMethod(id: number) {
    if (this.isHidden[id] == null) {
      for (let i of this.isHidden) {
        i = true;
      }
      this.isHidden[id] = false;

    } else {
      this.isHidden[id] = !this.isHidden[id];
    }
  }


  filterByUser(username: string) {
    console.log(username);
    this.adminService.filterByUser(username)
      .subscribe(data => console.log(this.courseListByUser = data));
  }

  filterByTrainer(id: number) {
    this.adminService.filterByTrainer(id)
      .subscribe(data => this.courseListByTrainer = data);
  }

  filterBySkillLevel(level: string) {
    this.adminService.filterBySkillLevel(level)
      .subscribe(data => this.courseListByLevel = data);
  }

  onTrainerSelected(val: any) {
    this.filterByTrainer(val);
    console.log(val)
  }

  onLevelSelected(val: any) {
    this.filterBySkillLevel(val);
    console.log(val);
  }

  setSelectAll(selected: boolean) {
    this.isSelectAllSelected = selected;
    this.selectedCourseArray.forEach(course => {
      course.isSelected = selected;
    });
  }

  setChooseCousesMode() {
    this.isChooseCoursesSelected = true;
    this.setSelectedCoursesArray();
    console.log(this.selectedCourseArray)
  }

  createReportByCourse() {
    this.selectedCoursesForReport = [];
    this.selectedCourseArray.forEach(course => {
      if (course.isSelected)
        this.selectedCoursesForReport.push(course.id);
    });
    console.log(this.selectedCoursesForReport);
    this.adminService.createReportByCourse(this.selectedCoursesForReport)
      .subscribe(
      data => console.log(data),
      error => console.log(error));
  }

  createReportByTrainer() {
    this.adminService.createReportByTrainer(this.trainerSelected)
    .subscribe(
      data => console.log(data),
      error => console.log(error))
  }

  createReportByStudent() { 
    this.adminService.createReportByStudent(this.userValue)
    .subscribe(
      data => console.log(data),
      error => console.log(error)) 
    }

  createReportByLevel() { 
    this.adminService.createReportByLevel(this.levelSelected.toString())
    .subscribe(
      data => console.log(data),
      error => console.log(error)) 
    }
}
