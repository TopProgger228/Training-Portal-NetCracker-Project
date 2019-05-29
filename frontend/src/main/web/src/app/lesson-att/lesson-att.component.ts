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
  currentTime: string = new Date().getHours() + ':' + new Date().getMinutes() + ':'+  new Date().getSeconds();

  ngOnInit() {
  }

  isLess(): boolean {
    if (this.lesson.lessonDate.toString()+ " " + this.lesson.endTime < this.dateAsYYYYMMDD(this.currentDate)+ " " + this.currentTime ) {
      return true;
    } else {
      return false;
    }
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
