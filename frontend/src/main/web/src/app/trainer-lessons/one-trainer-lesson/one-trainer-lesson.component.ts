import { Component, OnInit, Input } from '@angular/core';
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

  constructor(private trainerService: TrainerService, private toasterService: ToasterService) { }

  ngOnInit() {
  }

  setActive() {
    this.lesson.cancel = !this.lesson.cancel;
    this.trainerService.changelessonActiveStatus(this.lesson.lessonId.toString(), this.lesson.cancel.toString())
      .subscribe(()=>{},
        () =>{
          this.error()
        },
        ()=>{
          this.success(this.lesson);
        });
  }

  success(lesson: Lesson) {
    this.toasterService.Success("Success", "Lesson " + lesson.lessonDate.toString() + " " + lesson.startTime.toString() + " \nis " + (lesson.cancel ? "canceled!" : "activated!"));
  }

  error() {
    this.toasterService.Error("Error!");
  }

}
