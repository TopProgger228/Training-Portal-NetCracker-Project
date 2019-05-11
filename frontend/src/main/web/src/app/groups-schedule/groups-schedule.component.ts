import { Component, OnInit } from '@angular/core';
import { Timeslot } from "./timeslot";
import { Router } from "@angular/router";
import { TokenStorageService } from "../auth/token-storage.service";
import { TimeSlotService } from "./time-slot.service";
import { CoursesService } from "./courses.service";
import { Courses } from "./courses";
import { Schedule } from "../services/schedule";
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
    private coursesService: CoursesService,
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
  

  logout() {
    this.loggedout = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}
