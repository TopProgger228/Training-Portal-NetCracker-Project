import { Component, OnInit } from '@angular/core';
import { Timeslot } from "./timeslot";
import { Router } from "@angular/router";
import { TokenStorageService } from "../auth/token-storage.service";
import { TimeSlotService } from "./time-slot.service";
import { CoursesService } from "./courses.service";
import { Courses } from "./courses";
import { Schedule } from "../interface/schedule";
import { Timeslots } from "./timeslots";
import * as Util from "util";
import {ToasterService} from "../services/toaster.service";

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
    private coursesService: CoursesService, private token: TokenStorageService, private toasterService: ToasterService) {
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

  success() {
    this.toasterService.Success("Success","Timeslot was formed successfully");
  }

  error() {
    this.toasterService.Error("Error!", "Timeslot already exists");
  }

  onSubmitT() {
    console.log(this.timeSlotT);

    this.timeSlotService.addTimeslot(this.timeSlotT).subscribe(
      value => {
        console.log('[POST] create timeslot successfully', value);
      }, error => {
        console.log('FAIL to create timeslot!');
        this.error();
      },
      () => {
        console.log('POST timeslot - now completed.');
        this.success();
      });
  }
  

  logout() {
    this.loggedout = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }


}
