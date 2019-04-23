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
import {SignUpInfo} from "../auth/signup-info";

@Component({
  selector: 'app-groups-schedule',
  templateUrl: './groups-schedule.component.html',
  styleUrls: ['./groups-schedule.component.css']
})
export class GroupsScheduleComponent implements OnInit {

  form: any = {};
  timeSlots: Timeslot[];
  courses: Courses[];
  students: UserModel[];
  studySchedule: StudySchedule;

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

  onSubmit(){
    console.log(this.form);

    this.studySchedule = new StudySchedule(
      //this.form.id,
      this.form.course_id,
      this.form.user_id,
      this.form.time_slot_id,
      this.form.is_choosen);

    this.scheduleService.createStudySchedule(this.studySchedule).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['auth/login']);
      },
      error => {
        console.log(error);
      }
    );
  }

  logout() {
    this.loggedout = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}
