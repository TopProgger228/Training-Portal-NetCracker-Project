import { Component, OnInit } from '@angular/core';
import {LessonAtt} from "../interface/lesson-att";
import {TrainerAttService} from "../services/trainer-att.service";
import {TokenStorageService} from "../auth/token-storage.service";

@Component({
  selector: 'app-trainer-attendance',
  templateUrl: './trainer-attendance.component.html',
  styleUrls: ['./trainer-attendance.component.css']
})
export class TrainerAttendanceComponent implements OnInit {

  public lessonList: LessonAtt[];


  constructor(private trainerService: TrainerAttService, private token: TokenStorageService) { }

  ngOnInit() {

    // this.trainerService.getLessons()
    //   .subscribe(data => this.lessonList = data);

    this.trainerService.getLessonsByUsername(this.token.getUsername())
      .subscribe(data => this.lessonList = data);

  }

}
