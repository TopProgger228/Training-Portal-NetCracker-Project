import { Component, OnInit } from '@angular/core';
import { Timeslot } from "./timeslot";
import { Router } from "@angular/router";
import { TokenStorageService } from "../auth/token-storage.service";
import { TimeSlotService } from "./time-slot.service";
import { CoursesService } from "./courses.service";
import { Courses } from "./courses";
import { Schedule } from "../services/schedule";
import { Timeslots } from "./timeslots";
import * as Util from "util";

@Component({
  selector: 'app-groups-schedule',
  templateUrl: './groups-schedule.component.html',
  styleUrls: ['./groups-schedule.component.css']
})
export class GroupsScheduleComponent implements OnInit {
  loggedout = false;
  timeSlots: Timeslots[];
  sentMessage: string = null;
  chosenCourse: number = 1; //Java Beginner
  timeSlotT = new Timeslot("", "", "Monday", this.chosenCourse);
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

  setSentMessage(data: any) {
    if (data.body) {
      if (data.statusText === "OK") this.sentMessage = "Mail sent successfully";
      else if (data.statusText === "BAD_REQUEST") this.sentMessage = "User does not exist"
      else this.sentMessage = "Something going wrong, try later";
    }
    else this.sentMessage = "Mail does not sent yet";
  }

}
