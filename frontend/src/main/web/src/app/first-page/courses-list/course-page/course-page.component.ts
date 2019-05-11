import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../../../auth/token-storage.service";
import {CourseService} from "../../../services/course.service";
import {Course} from "../../../services/course";
import {Timeslots} from "../../../groups-schedule/timeslots";
import {Schedule} from "../../../services/schedule";
import {UserModel} from "../../../services/user-model";
import {Student} from "../../../services/student";

@Component({
  selector: 'app-course-page',
  templateUrl: './course-page.component.html',
  styleUrls: ['./course-page.component.css']
})
export class CoursePageComponent implements OnInit {

  course: Course[];
  student: UserModel[];
  name: string;
  username: string;
  userId : number;
  timeSlots: Timeslots[];
  schedule = new Schedule(0, 0, false);

  constructor(private route: ActivatedRoute, private router: Router, private courseService: CourseService,
              private token: TokenStorageService) {
  }

  ngOnInit() {
    const _this = this;
    if (this.token.getToken()) {

      this.route.params.subscribe(params => {
        this.name = params['name']; // (+) converts string 'id' to a number
        this.username = params['username'];
        console.log('Name', this.name)
        console.log('Username', this.token.getUsername())
        // In a real app: dispatch action to load the details here.
      });


      this.courseService.getCoursePage(this.name)
        .subscribe(data => {
          this.course = data;
        });
      this.courseService.getTimeSlots(this.name)
        .subscribe(data => {
          this.timeSlots = data;
        });

      this.courseService.getIdByUsername(this.token.getUsername())
        .subscribe(data => {
          this.userId = data;
          console.log('User id1', this.userId)
          this.schedule = new Schedule(this.userId, 0, false);
        });
      console.log('Username', this.token.getUsername())

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
