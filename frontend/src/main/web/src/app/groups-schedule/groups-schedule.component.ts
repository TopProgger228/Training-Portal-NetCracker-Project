import { Component, OnInit } from '@angular/core';
import {Timeslot} from "./timeslot";
import {Router} from "@angular/router";
import {TokenStorageService} from "../auth/token-storage.service";
import {TimeSlotServiceService} from "./time-slot-service.service";
import {Course} from "../first-page/courses-list/course";
import {CoursesService} from "./courses.service";

@Component({
  selector: 'app-groups-schedule',
  templateUrl: './groups-schedule.component.html',
  styleUrls: ['./groups-schedule.component.css']
})
export class GroupsScheduleComponent implements OnInit {

  timeSlots: Timeslot[];
  courses: Course[];

  loggedout = false;

  constructor(private router: Router, private timeSlotService: TimeSlotServiceService, private courseService: CoursesService,private token: TokenStorageService) { }

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
