import { Component, OnInit, Input } from '@angular/core';
import {Lesson} from "../../interface/lesson";

@Component({
  selector: 'app-one-trainer-lesson',
  templateUrl: './one-trainer-lesson.component.html',
  styleUrls: ['./one-trainer-lesson.component.css']
})
export class OneTrainerLessonComponent implements OnInit {

  @Input('lesson') lesson: Lesson;
  @Input('index') index: number;

  constructor() { }

  ngOnInit() {
  }

}
