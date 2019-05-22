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
  currentTime: string = new Date().getHours() + ':' + new Date().getMinutes() + ':'+  new Date().getSeconds()

  ngOnInit() {
    console.log("lesson:" + this.lesson.startDateTime.toString()+ " " + this.lesson.endTime)
  console.log("current: " + this.dateAsYYYYMMDD(this.currentDate) + " " + this.currentTime)
    console.log(this.lesson.startDateTime.toString()+ " " + this.lesson.endTime < this.dateAsYYYYMMDD(this.currentDate)+ " " + this.currentTime)
  }

  isLess(): boolean {
    if (this.lesson.startDateTime.toString()+ " " + this.lesson.endTime < this.dateAsYYYYMMDD(this.currentDate)+ " " + this.currentTime ) {
      return true;
      console.log(true)
    } else {
      return false;
      console.log(false)
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
