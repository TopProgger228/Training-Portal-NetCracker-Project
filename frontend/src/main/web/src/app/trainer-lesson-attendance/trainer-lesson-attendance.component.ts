import { Component, OnInit } from '@angular/core';
import {Class} from "../interface/class";
import {TrainerAttService} from "../services/trainer-att.service";

@Component({
  selector: 'app-trainer-lesson-attendance',
  templateUrl: './trainer-lesson-attendance.component.html',
  styleUrls: ['./trainer-lesson-attendance.component.css']
})
export class TrainerLessonAttendanceComponent implements OnInit {

  class: Class;
  statusList: string[] = [
    "Present",
    "Absent due to business trip",
    "Absent without reason",
    "Absent due to sick leave",
    "Absent due to project activities"
  ]
  oneStatus: string;

  constructor(private trainerServise: TrainerAttService) { }

  ngOnInit() {

    this.trainerServise.getOneLesson()
      .subscribe(data => this.class = data);

  }

  putAttendanceStatus (userId: string, lessonId: string, status: string) {
    this.trainerServise.putAttendanceStatus(userId, lessonId, status)
      .subscribe(data=>{
        console.log(data);
      });
  }

}
