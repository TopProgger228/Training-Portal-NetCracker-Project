import { Component, OnInit } from '@angular/core';
import {CourseAtt} from "../../interface/course-att";
import {AdminAttService} from "../../services/admin-att.service";
import {ToasterService} from "../../services/toaster.service";

@Component({
  selector: 'app-by-student-courses-admin-att',
  templateUrl: './by-student-courses-admin-att.component.html',
  styleUrls: ['./by-student-courses-admin-att.component.css']
})
export class ByStudentCoursesAdminAttComponent implements OnInit {

  courseList: CourseAtt[];
  isReset: boolean = false;
  userValue: string;
  isLoading: boolean = false;


  constructor(private adminService: AdminAttService, private toasterService: ToasterService) { }

  ngOnInit() {
  }

  filterByUser(username: string) {
    this.isLoading = true;
    this.isReset = true;
    console.log(username);
    this.adminService.filterByUser(username)
      .subscribe(data => { this.courseList = data; this.isLoading = false; this.isReset = false });
  }

  createReportByStudent() {
    this.adminService.createReportByStudent(this.userValue)
      .subscribe(
        data => {
          console.log(data);

          if (data.body)
            this.toasterService.Success("Success", "Report download successfully");
        },
        error => {
          console.log(error);

          this.toasterService.Error("Error!", "Http failure response");
        });
  }

}
