import {Component, Input, OnInit} from '@angular/core';
import {CourseAtt} from "../../interface/course-att";
import {AdminAttService} from "../../services/admin-att.service";
import {ToasterService} from "../../services/toaster.service";


@Component({
  selector: 'app-all-courses-admin-att',
  templateUrl: './all-courses-admin-att.component.html',
  styleUrls: ['./all-courses-admin-att.component.css']
})
export class AllCoursesAdminAttComponent implements OnInit {

  public courseList: CourseAtt[];
  public isReset: boolean;
  isLoading: boolean = true;

  isChooseCoursesSelected: boolean;
  isSelectAllSelected: boolean;

  selectedCourseArray: Array<{ id: any, isSelected: any }> = [];
  selectedCoursesForReport: Array<number> = [];



  folder: string;

  constructor(private adminService: AdminAttService,
              private toasterService: ToasterService) { }

  ngOnInit() {

    this.adminService.getCourses()
      .subscribe(data => { this.courseList = data; this.isLoading = false });
    this.isChooseCoursesSelected = false;
    this.isSelectAllSelected = true;
  }

  setSelectedCoursesArray() {
    this.selectedCourseArray = [];
    this.courseList.forEach(course => {
      this.selectedCourseArray.push({ id: course.courseId, isSelected: true });
    });
  }

  setSelectAll(selected: boolean) {
    this.isSelectAllSelected = selected;
    this.selectedCourseArray.forEach(course => {
      course.isSelected = selected;
    });
  }

  setChooseCoursesMode() {
    this.isChooseCoursesSelected = true;
    this.setSelectedCoursesArray();
    console.log(this.selectedCourseArray)
  }

  createReportByCourse() {
    this.selectedCoursesForReport = [];
    this.selectedCourseArray.forEach(course => {
      if (course.isSelected)
        this.selectedCoursesForReport.push(course.id);
    });
    console.log(this.selectedCoursesForReport);
    this.adminService.createReportByCourse(this.selectedCoursesForReport)
      .subscribe(
        data => {
          console.log(data);

          if (data.body)
            this.toasterService.Success("Success", "Added to /Download folder");
        },
        error => {
          console.log(error);

          this.toasterService.Error("Error!", "Http failure response");
        });
      }
}
