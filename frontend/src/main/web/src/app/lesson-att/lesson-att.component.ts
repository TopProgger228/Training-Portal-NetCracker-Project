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

  currentDate: Date = new Date();
  currentTime: string = ("0" + new Date().getHours()).slice(-2) + ':' + ("0" + new Date().getMinutes()).slice(-2) + ':'+ ("0" + new Date().getSeconds()).slice(-2);

  ngOnInit() {
  }

  isLess(): boolean {
    return this.lesson.lessonDate.toString() + " " + this.lesson.endTime < this.dateAsYYYYMMDD(this.currentDate) + " " + this.currentTime;
  }

  dateAsYYYYMMDD(date): string {
    return date.getFullYear()
      + '-' + this.leftpad(date.getMonth() + 1, 2)
      + '-' + this.leftpad(date.getDate(), 2);
  }

  leftpad(val, resultLength = 2, leftpadChar = '0'): string {
    return (String(leftpadChar).repeat(resultLength)
      + String(val)).slice(String(val).length);
  }

}
