import {Component, OnInit} from '@angular/core';
import {CourseAtt} from "../../interface/course-att";
import {AdminAttService} from "../../services/admin-att.service";
import {ToasterService} from "../../services/toaster.service";

@Component({
  selector: 'app-by-level-courses-admin-att',
  templateUrl: './by-level-courses-admin-att.component.html',
  styleUrls: ['./by-level-courses-admin-att.component.css']
})
export class ByLevelCoursesAdminAttComponent implements OnInit {

  courseList: CourseAtt[];
  levelSelected: string;
  levelSelector: string[];
  isReset: boolean = false;
  isLoading: boolean = false;


  constructor(private adminService: AdminAttService, private toasterService: ToasterService) { }

  ngOnInit() {
    this.levelSelector = [
      "Beginner", "Junior", "Middle", "Master"
    ]
  }

  createReportByLevel() {
    this.adminService.createReportByLevel(this.levelSelected.toString())
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

  onLevelSelected(val: any) {
    this.isLoading = true;
    this.isReset = true;
    this.filterBySkillLevel(val);
  }
  filterBySkillLevel(level: string) {
    this.adminService.filterBySkillLevel(level)
      .subscribe(data => { this.courseList = data; this.isLoading = false; this.isReset = false });
  }

}
