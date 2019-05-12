import { Component, OnInit, Input } from '@angular/core';
import {LessonAtt} from "../interface/lesson-att";

@Component({
  selector: 'app-lesson-att',
  templateUrl: './lesson-att.component.html',
  styleUrls: ['./lesson-att.component.css']
})
export class LessonAttComponent implements OnInit {

  @Input('index') public index: number;
  @Input('lesson') public lesson: LessonAtt;

  ngOnInit() {
  }

}
