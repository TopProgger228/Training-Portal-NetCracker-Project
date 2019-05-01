import { Component, OnInit } from '@angular/core';
import {Timeslots} from "../groups-schedule/timeslots";
import {Timeslot} from "../groups-schedule/timeslot";
import {Courses} from "../groups-schedule/courses";
import {Schedule} from "../groups-schedule/schedule";
import {Router} from "@angular/router";
import {TimeSlotService} from "../groups-schedule/time-slot.service";
import {CoursesService} from "../groups-schedule/courses.service";
import {ScheduleService} from "../groups-schedule/schedule.service";
import {TokenStorageService} from "../auth/token-storage.service";

@Component({
  selector: 'app-my-schedule',
  templateUrl: './my-schedule.component.html',
  styleUrls: ['./my-schedule.component.css']
})
export class MyScheduleComponent implements OnInit {

  loggedout = false;
  timeSlots: Timeslots[];
  timeSlotT = new Timeslot("", "", "",0);
  courses: Courses[];
  schedule = new Schedule(0,0,false);

  constructor(private router: Router,  private timeSlotService: TimeSlotService,
              private coursesService: CoursesService, private scheduleService: ScheduleService,
              private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.timeSlotService.getTimeSlots()
        .subscribe(data => {
          this.timeSlots = data;
        });
      this.coursesService.getCourses()
        .subscribe(data => {
          this.courses = data;
        })
    } else {
      this.loggedout = true;
      this.router.navigate(['auth/login']);
    };
  };

  submitted = false;

  onSubmit(){
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

  logout() {
    this.loggedout = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}
