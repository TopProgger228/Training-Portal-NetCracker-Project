import { Component, OnInit, Input } from '@angular/core';
import {CourseAtt} from "../interface/course-att";
import {ManagerAttService} from "../services/manager-att.service";
import {LessonAtt} from "../interface/lesson-att";

@Component({
  selector: 'app-user-att-course-list',
  templateUrl: './user-att-course-list.component.html',
  styleUrls: ['./user-att-course-list.component.css']
})
export class UserAttCourseListComponent implements OnInit {

  @Input('courseAtt') public course: CourseAtt;
  @Input('index') public index: number;
  @Input('userId') public userId: number;
  lessonList: LessonAtt[];



  hide: boolean = true;

  isLoading: boolean = true;

  constructor(private managerService: ManagerAttService) { }

  ngOnInit() {
    this.managerService.getLessonsForOneUser(this.course.courseId, this.userId)
      .subscribe(data=>{this.lessonList = data; this.isLoading = false});

  }

  isHidden(){
    this.hide = !this.hide;
    console.log(this.course.name)
  }

}

