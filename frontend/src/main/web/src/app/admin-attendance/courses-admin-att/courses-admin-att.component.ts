import {Component, Input, Output, OnInit, EventEmitter} from '@angular/core';
import {CourseAtt} from "../../interface/course-att";
import {LessonAtt} from "../../interface/lesson-att";
import {AdminAttService} from "../../services/admin-att.service";

@Component({
  selector: 'app-courses-admin-att',
  templateUrl: './courses-admin-att.component.html',
  styleUrls: ['./courses-admin-att.component.css']
})
export class CoursesAdminAttComponent implements OnInit {

  @Input('courseList') public courseList: CourseAtt[];
  @Input('isTrainerColumnNeed') public isTrainerColumnNeed: boolean = true;
  @Input('isReset') public isReset: boolean = false;
  @Input('isChooseCoursesSelected') public isChooseCoursesSelected: boolean;
  @Input('isSelectAllSelected') public isSelectAllSelected: boolean;
  @Input('selectedCourseArray') public selectedCourseArray: Array<{ id: any, isSelected: any }> = [];

  @Output() public childEvent = new EventEmitter();

  lessonList: LessonAtt[];

  chosenOneCourse: number = -1;
  isLessonLoading: boolean = false;

  constructor(private adminService: AdminAttService) {
  }

  ngOnInit() {
  }

  selectCourseForReport(courseId: number) {
    this.selectedCourseArray.forEach(course => {
      if (course.id == courseId) {
        course.isSelected = !course.isSelected;
      }
    });
    this.childEvent.emit(this.selectedCourseArray);
    console.log(this.selectedCourseArray);
  }

  setChosenCourse(id: number) {
    if (this.chosenOneCourse === id) {
      this.chosenOneCourse = -1;
    } else {
      this.isLessonLoading = true;
      this.chosenOneCourse = id;
      this.getLessons(id);
    }
  }

  getLessons(id: number) {
    this.lessonList = [];
    this.adminService.getLessons(id)
      .subscribe(data => {
        this.lessonList = data;
        this.isLessonLoading = false;
      });
  }

}
