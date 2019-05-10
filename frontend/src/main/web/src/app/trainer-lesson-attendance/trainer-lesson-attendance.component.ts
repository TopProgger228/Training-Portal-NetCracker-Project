import { Component, OnInit } from '@angular/core';
import {Class} from "../interface/class";
import {TrainerAttService} from "../services/trainer-att.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-trainer-lesson-attendance',
  templateUrl: './trainer-lesson-attendance.component.html',
  styleUrls: ['./trainer-lesson-attendance.component.css']
})
export class TrainerLessonAttendanceComponent implements OnInit {

  id: number;
  class: Class;
  statusList: string[] = [
    "Present",
    "Absent due to business trip",
    "Absent without reason",
    "Absent due to sick leave",
    "Absent due to project activities"
  ]
  oneStatus: string;

  constructor(private trainerService: TrainerAttService, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(param => {
    this.id = param['id']});
  }

  ngOnInit() {

    console.log(this.id);
    this.trainerService.getOneLesson(this.id)
      .subscribe(data => this.class = data);


  }

  putAttendanceStatus (userId: string, lessonId: string, status: string) {
    this.trainerService.putAttendanceStatus(userId, lessonId, status)
      .subscribe(data=>{
        console.log(data);
      });
  }

}
