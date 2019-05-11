import { Component, OnInit } from '@angular/core';
import { Timeslots } from "../groups-schedule/timeslots";
import { Timeslot } from "../groups-schedule/timeslot";
import { Courses } from "../groups-schedule/courses";
import { Schedule } from "../services/schedule";
import { Router } from "@angular/router";
import { TimeSlotService } from "../groups-schedule/time-slot.service";
import { CoursesService } from "../groups-schedule/courses.service";
import { TokenStorageService } from "../auth/token-storage.service";

@Component({
  selector: 'app-my-schedule',
  templateUrl: './my-schedule.component.html',
  styleUrls: ['./my-schedule.component.css']
})
export class MyScheduleComponent implements OnInit {

  loggedout = false;
  timeSlots: Timeslots[];
  timeSlotT = new Timeslot("", "", "", 0);
  courses: Courses[];
  schedule = new Schedule(0, 0, false);

  constructor(private router: Router, private timeSlotService: TimeSlotService,
    private coursesService: CoursesService,
    private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Student') {
          this.timeSlotService.getTimeSlots()
            .subscribe(data => {
              this.timeSlots = data;
            });
          this.coursesService.getCourses()
            .subscribe(data => {
              this.courses = data;
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


  logout() {
    this.loggedout = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}
