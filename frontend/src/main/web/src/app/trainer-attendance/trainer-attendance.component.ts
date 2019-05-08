import { Component, OnInit } from '@angular/core';
import {LessonAtt} from "../interface/lesson-att";
import {TrainerAttService} from "../services/trainer-att.service";

@Component({
  selector: 'app-trainer-attendance',
  templateUrl: './trainer-attendance.component.html',
  styleUrls: ['./trainer-attendance.component.css']
})
export class TrainerAttendanceComponent implements OnInit {

  public lessonList: LessonAtt[];


  constructor(private trainerServise: TrainerAttService) { }

  ngOnInit() {

    this.trainerServise.getLessons()
      .subscribe(data => this.lessonList = data);

  }

}
