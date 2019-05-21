import { Component, OnInit, Input } from '@angular/core';
import {ManagerUserAtt} from "../interface/manager-user-att";
import {ManagerAttService} from "../services/manager-att.service";
import {CourseAtt} from "../interface/course-att";
import {animate, state, style, transition, trigger} from '@angular/animations';
import {TrainerAtt} from "../interface/trainer-att";
import {MngCourse} from "../interface/mng-course";

@Component({
  selector: 'app-one-studet-mng-att',
  templateUrl: './one-studet-mng-att.component.html',
  styleUrls: ['./one-studet-mng-att.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0', display: 'none'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
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
    this.managerService.getCourseByUserId(this.user.id).subscribe(
      data=> {this.courseList = data; this.isLoading = false;});
  }

  isHidden() {
    this.hide = !this.hide;
  }

}

