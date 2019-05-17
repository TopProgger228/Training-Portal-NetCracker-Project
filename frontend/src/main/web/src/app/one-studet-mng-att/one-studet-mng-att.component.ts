import { Component, OnInit, Input } from '@angular/core';
import {ManagerAttendanceComponent} from "../manager-attendance/manager-attendance.component";
import {ManagerUserAtt} from "../interface/manager-user-att";
import {ManagerAttService} from "../services/manager-att.service";
import {CourseAtt} from "../interface/course-att";

@Component({
  selector: 'app-one-studet-mng-att',
  templateUrl: './one-studet-mng-att.component.html',
  styleUrls: ['./one-studet-mng-att.component.css']
})
export class OneStudetMngAttComponent implements OnInit {

  @Input('userData') public user: ManagerUserAtt;
  @Input('mapData') public statusList: Map<string, number>;
  courseList: CourseAtt[];

  hide: boolean = true;
  isLoading: boolean = true;

  constructor(private managerService: ManagerAttService) {

  }

  ngOnInit() {
    this.managerService.getCourseByUserId(this.user.id)
      .subscribe(data=>{this.courseList = data; this.isLoading = false});
  }

  isHidden() {
    this.hide = !this.hide;
  }

}
