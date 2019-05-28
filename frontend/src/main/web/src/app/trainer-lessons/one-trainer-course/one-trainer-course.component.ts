import {Component, Input, OnInit} from '@angular/core';
import {CourseForTrainer} from "../../interface/course-for-trainer";
import {Lesson} from "../../interface/lesson";
import {TrainerService} from "../../services/trainer.service";
import {CourseService} from "../../services/course.service";
import {TokenStorageService} from "../../auth/token-storage.service";

@Component({
  selector: 'app-one-trainer-course',
  templateUrl: './one-trainer-course.component.html',
  styleUrls: ['./one-trainer-course.component.css']
})
export class OneTrainerCourseComponent implements OnInit {

  courseList: CourseForTrainer[];
  lessonList: Lesson[];

  isReadyToShow: boolean  = false;
  isReadyToShowLesson: boolean;
  chosenOneCourse: number = -1;
  isCourseLoading: boolean = true;
  isLessonLoading: boolean = false;

  constructor(private trainerService: TrainerService, private courseService: CourseService, private token: TokenStorageService) {
  }

  ngOnInit() {
    this.courseList = [];
    this.courseService.getCoursesForTrainerByTrainerUsername(this.token.getUsername())
      .subscribe(data => {
        this.courseList = data;
        this.isCourseLoading = false;
        this.isReadyToShow = true;
      });
  }

  setChosenCourse(id: number) {
    if (this.chosenOneCourse === id) {
      this.chosenOneCourse = -1;
    } else {
      this.isReadyToShowLesson = false;
      this.isLessonLoading = true;
      this.chosenOneCourse = id;
      this.getLessons(id);
    }
  }

  getLessons(id: number) {
    this.lessonList = [];
    this.trainerService.getLessonByCourse(id)
      .subscribe(data => {
        this.lessonList = data;
        this.isLessonLoading = false;
        this.isReadyToShowLesson = true;
      });
  }

}
