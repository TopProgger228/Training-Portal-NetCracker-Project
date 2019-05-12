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


  constructor(private trainerService: TrainerAttService, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(param => {
    this.id = param['id']});
  }

  ngOnInit() {

    console.log(this.id);
    this.trainerService.getOneLesson(this.id)
      .subscribe(data => this.class = data);


  }

}
