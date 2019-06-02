import {Component, OnInit, Input} from '@angular/core';
import {Lesson} from "../../interface/lesson";
import {TrainerService} from "../../services/trainer.service";
import {ToasterService} from "../../services/toaster.service";

@Component({
  selector: 'app-one-trainer-lesson',
  templateUrl: './one-trainer-lesson.component.html',
  styleUrls: ['./one-trainer-lesson.component.css']
})
export class OneTrainerLessonComponent implements OnInit {

  @Input('lesson') lesson: Lesson;
  @Input('index') index: number;
  currentDate: Date = new Date();
  currentTime: string = ("0" + new Date().getHours()).slice(-2) + ':' + ("0" + new Date().getMinutes()).slice(-2) + ':'+ ("0" + new Date().getSeconds()).slice(-2);

  constructor(private trainerService: TrainerService, private toasterService: ToasterService) {
  }

  ngOnInit() {
  }

  setActive() {
    this.lesson.cancel = !this.lesson.cancel;
    this.trainerService.changelessonActiveStatus(this.lesson.lessonId.toString(), this.lesson.cancel.toString())
      .subscribe(() => {
        },
        () => {
          this.error()
        },
        () => {
          this.success(this.lesson);
        });
  }

  success(lesson: Lesson) {
    this.toasterService.Success("Success", "Lesson " + lesson.lessonDate.toString() + " "
      + lesson.startTime.toString() + " \nis " + (lesson.cancel ? "canceled!" : "activated!"));
  }

  error() {
    this.toasterService.Error("Error!");
  }

  isLess(): boolean {
    return this.lesson.lessonDate.toString() + " " + this.lesson.startTime < this.dateAsYYYYMMDD(this.currentDate) + " " + this.currentTime;
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
