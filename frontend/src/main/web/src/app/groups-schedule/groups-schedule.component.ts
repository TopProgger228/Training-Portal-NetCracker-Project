import {Component, OnInit} from '@angular/core';
import {Timeslot} from "./timeslot";
import {Router} from "@angular/router";
import {TokenStorageService} from "../auth/token-storage.service";
import {TimeSlotServiceService} from "./time-slot-service.service";
import {CoursesService} from "./courses.service";
import {Courses} from "./courses";
import {ScheduleService} from "./schedule.service";
import {Schedule} from "./schedule";

@Component({
  selector: 'app-groups-schedule',
  templateUrl: './groups-schedule.component.html',
  styleUrls: ['./groups-schedule.component.css']
})
export class GroupsScheduleComponent implements OnInit {
  loggedout = false;
  timeSlots: Timeslot[];
  courses: Courses[];
  schedule = new Schedule(0,0,0,false);

  constructor(private router: Router, private timeSlotService: TimeSlotServiceService,
              private coursesService: CoursesService, private httpService: ScheduleService,
              private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.timeSlotService.getTimeSlots()
        .subscribe(data => {
          this.timeSlots = data;
        })
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
    this.httpService.createStudySchedule(this.schedule).subscribe(
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
