import { Component, OnInit } from '@angular/core';
import {CourseAtt} from "../../interface/course-att";
import {AdminAttService} from "../../services/admin-att.service";
import {ToasterService} from "../../services/toaster.service";

@Component({
  selector: 'app-by-trainer-courses-admin-att',
  templateUrl: './by-trainer-courses-admin-att.component.html',
  styleUrls: ['./by-trainer-courses-admin-att.component.css']
})
export class ByTrainerCoursesAdminAttComponent implements OnInit {

  courseList: CourseAtt[];
  isReset: boolean = false;
  trainerValue: string;
  isLoading: boolean = false;

  constructor(private adminService: AdminAttService, private toasterService: ToasterService) { }

  ngOnInit() {
  }

  createReportByTrainer() {
    this.adminService.createReportByTrainer(this.trainerValue)
      .subscribe(
        data => console.log(data),
        error => console.log(error))
  }

  filterByTrainerUsername(username: string) {
    this.isLoading = true;
    this.isReset = true;
    console.log(username);
    this.adminService.filterByTrainerUsername(username)
      .subscribe(data => { this.courseList = data; this.isLoading = false; this.isReset = false; });
  }

}
