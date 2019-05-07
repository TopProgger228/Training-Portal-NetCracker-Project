import { Component, OnInit } from '@angular/core';
import { Timeslot } from "./timeslot";
import { Router } from "@angular/router";
import { TokenStorageService } from "../auth/token-storage.service";
import { TimeSlotService } from "./time-slot.service";
import { CoursesService } from "./courses.service";
import { Courses } from "./courses";
import { ScheduleService } from "./schedule.service";
import { Schedule } from "./schedule";
import { Timeslots } from "./timeslots";

@Component({
  selector: 'app-groups-schedule',
  templateUrl: './groups-schedule.component.html',
  styleUrls: ['./groups-schedule.component.css']
})
export class GroupsScheduleComponent implements OnInit {
  loggedout = false;
  timeSlots: Timeslots[];
  timeSlotT = new Timeslot("", "", "", 0);
  courses: Courses[];
  schedule = new Schedule(0, 0, false);

  constructor(private router: Router, private timeSlotService: TimeSlotService,
    private coursesService: CoursesService, private scheduleService: ScheduleService,
    private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Admin') {
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

  onSubmit() {
    console.log(this.schedule);
    this.scheduleService.createSchedule(this.schedule).subscribe(
      value => {
        console.log('[POST] create schedule successfully', value);
      }, error => {
        console.log('FAIL to create schedule!');
      },
      () => {
        console.log('POST schedule - now completed.');
      });
  }

  onSubmitT() {
    console.log(this.timeSlotT);
    this.timeSlotService.addTimeslot(this.timeSlotT).subscribe(
      value => {
        console.log('[POST] create timeslot successfully', value);
      }, error => {
        console.log('FAIL to create timeslot!');
      },
      () => {
        console.log('POST timeslot - now completed.');
      });
  }

  // public static compareTime(time1: Date, time2: Time): number
  // {
  //   // With Date object we can compare dates them using the >, <, <= or >=.
  //   // The ==, !=, ===, and !== operators require to use date.getTime(),
  //   // so we need to create a new instance of Date with 'new Date()'
  //   let d1 = new Date(time1); let d2 = new Time(time2);
  //
  //   // Check if the dates are equal
  //   let same = d1.getTime() === d2.getTime();
  //   if (same) return 0;
  //
  //   // Check if the first is greater than second
  //   if (d1 > d2) return 1;
  //
  //   // Check if the first is less than second
  //   if (d1 < d2) return -1;
  // }

  logout() {
    this.loggedout = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}
