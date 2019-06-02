import {Component, Input, OnInit} from '@angular/core';
import {UserAtt} from "../../interface/user-att";
import {Lesson} from "../../interface/lesson";

@Component({
  selector: 'app-one-student-admin-att',
  templateUrl: './one-student-admin-att.component.html',
  styleUrls: ['./one-student-admin-att.component.css']
})
export class OneStudentAdminAttComponent implements OnInit {

  @Input('userList') public userList: UserAtt[];
  @Input('lesson') public lesson: Lesson;

  currentDate: Date = new Date();
  currentTime: string = ("0" + new Date().getHours()).slice(-2) + ':' + ("0" + new Date().getMinutes()).slice(-2) + ':'+ ("0" + new Date().getSeconds()).slice(-2);

  constructor() { }

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
