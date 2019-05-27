import {Component, Input, OnInit} from '@angular/core';
import {LessonAtt} from "../../interface/lesson-att";
import {UserAtt} from "../../interface/user-att";
import {AdminAttService} from "../../services/admin-att.service";

@Component({
  selector: 'app-one-lesson-admin-att',
  templateUrl: './one-lesson-admin-att.component.html',
  styleUrls: ['./one-lesson-admin-att.component.css']
})
export class OneLessonAdminAttComponent implements OnInit {

  @Input('lesson') public lesson: LessonAtt;
  @Input('index') public index: number;
  userList: UserAtt[];
  isUserLoading: boolean = false;

  chosenOneLesson: number = -1;

  constructor(private adminService: AdminAttService) { }

  currentDate: Date = new Date();
  currentTime: string = new Date().getHours() + ':' + new Date().getMinutes() + ':'+  new Date().getSeconds();
  ngOnInit() {
  }

  setChosenLesson(id: number) {
    if (this.chosenOneLesson === id) {
      this.chosenOneLesson = -1;
      // this.isUserLoading = false;
    } else {
      this.isUserLoading = true;
      this.chosenOneLesson = id;
      this.getUsers(id);
    }
  }

  getUsers(id: number) {
      this.userList = [];
      this.adminService.getStudents(id)
        .subscribe(data => {this.userList = data; this.isUserLoading = false;});
    }

  isLess(): boolean {
    if (this.lesson.startDateTime.toString()+ " " + this.lesson.endTime < this.dateAsYYYYMMDD(this.currentDate)+ " " + this.currentTime ) {
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
