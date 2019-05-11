import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../../../auth/token-storage.service";
import {CourseService} from "../../../services/course.service";
import {Course} from "../../../services/course";
import {Timeslots} from "../../../groups-schedule/timeslots";
import {Schedule} from "../../../services/schedule";

@Component({
  selector: 'app-course-page',
  templateUrl: './course-page.component.html',
  styleUrls: ['./course-page.component.css']
})
export class CoursePageComponent implements OnInit {

  course: Course[];
  name: string;
  timeSlots: Timeslots[];
  schedule = new Schedule(0, 0, false);

  constructor(private route: ActivatedRoute, private router: Router, private courseService: CourseService,
              private token: TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()){
      this.route.params.subscribe(params => {
        this.name = params['name']; // (+) converts string 'id' to a number

        // In a real app: dispatch action to load the details here.
      });
      this.courseService.getCoursePage(this.name)
        .subscribe(data => {
          this.course = data;
        })
      this.courseService.getTimeSlots(this.name)
        .subscribe(data => {
          this.timeSlots = data;
        });
    }
  }

  onSubmit() {
    console.log(this.schedule);
    this.courseService.createSchedule(this.schedule).subscribe(
      value => {
        console.log('[POST] create schedule successfully', value);
      }, error => {
        console.log('FAIL to create schedule!');
      },
      () => {
        console.log('POST schedule - now completed.');
      });
  }

}
