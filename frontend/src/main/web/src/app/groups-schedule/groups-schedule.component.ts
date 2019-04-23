import { Component, OnInit } from '@angular/core';
import {Timeslot} from "./timeslot";
import {Router} from "@angular/router";
import {TokenStorageService} from "../auth/token-storage.service";
import {TimeSlotServiceService} from "./time-slot-service.service";
import {CoursesService} from "./courses.service";
import {Courses} from "./courses";
import {UserService} from "../services/user.service";
import {UserModel} from "../services/user-model";
import {StudySchedule} from "./study-schedule";
import {ScheduleService} from "./schedule.service";

@Component({
  selector: 'app-groups-schedule',
  templateUrl: './groups-schedule.component.html',
  styleUrls: ['./groups-schedule.component.css']
})
export class GroupsScheduleComponent implements OnInit {

  timeSlots: Timeslot[];
  courses: Courses[];
  students: UserModel[];
  studySchedule: StudySchedule = new StudySchedule();

  loggedout = false;
  submitted = false;

  constructor(private router: Router, private timeSlotService: TimeSlotServiceService,
              private courseService: CoursesService, private userService: UserService,
              private token: TokenStorageService, private scheduleService: ScheduleService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.timeSlotService.getTimeSlots()
        .subscribe(data => {
          this.timeSlots = data;
        })
      this.courseService.getCourses()
        .subscribe(data => {
          this.courses = data;
        })
      this.userService.getStudents()
        .subscribe(data => {
          this.students = data;
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
