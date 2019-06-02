import { Component, OnInit } from '@angular/core';
import {CourseAttendeesService} from "../services/course-attendees.service";
import {ActivatedRoute} from "@angular/router";
import {AttendeeName} from "../interface/attendee-name";

@Component({
  selector: 'app-course-attendees',
  templateUrl: './course-attendees.component.html',
  styleUrls: ['./course-attendees.component.css']
})
export class CourseAttendeesComponent implements OnInit {
  name : string;
  attendees : AttendeeName[];


  constructor(private service : CourseAttendeesService, private router : ActivatedRoute) {
    this.router.queryParams.subscribe(
      param => {this.name = param['name']}
    );
  }

  ngOnInit() {
    console.log(this.name);
    this.service.getCourseAttendees(this.name).subscribe(
      data => this.attendees = data
    );
  }

}
