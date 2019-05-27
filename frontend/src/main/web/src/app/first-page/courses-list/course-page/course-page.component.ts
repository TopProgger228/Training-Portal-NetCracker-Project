import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../../../auth/token-storage.service";
import {CourseService} from "../../../services/course.service";
import {Course} from "../../../services/course";
import {Timeslots} from "../../../groups-schedule/timeslots";
import {Schedule} from "../../../services/schedule";
import {UserModel} from "../../../services/user-model";
import {Subject} from "rxjs";
import {ToasterService} from "../../../services/toaster.service";
import {FormBuilder, FormControl, FormGroup, NgModel} from "@angular/forms";
import {MatSelect} from "@angular/material";

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
  userId: number;
  timeSlots: Timeslots[];
  schedule = new Schedule(0, 0, false);
  myIdArray: any[];
  private _success = new Subject<string>();


  constructor(private route: ActivatedRoute, private router: Router, private courseService: CourseService,
              private token: TokenStorageService, private toasterService: ToasterService) {
  }

  ngOnInit() {
    const _this = this;
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Student') {
          this.route.params.subscribe(params => {
            this.name = params['name']; // (+) converts string 'id' to a number
            this.username = params['username'];
            console.log('Name', this.name);
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
              console.log('This schedule = ', this.timeSlots);
              this.myIdArray = this.timeSlots.map(({id}) => id);
              console.log('Current id1 = ', this.myIdArray);
            });


          this.courseService.getIdByUsername(this.token.getUsername())
            .subscribe(data => {
              this.userId = data;
              console.log('User id1', this.userId);
              this.schedule = new Schedule(this.userId, 0, false);
            });
        } else {
          this.router.navigate(['firstPage']);
          this.warning();
        }
        return false;
      });
      console.log('Username', this.token.getUsername());
      /*
              this._success.subscribe((message) => this.successMessage = message);
              this._success.pipe(
                debounceTime(10000)
              ).subscribe(() => this.successMessage = null);
      */
    }

  }

  selectAll(select: NgModel, values) {
    select.update.emit(values);
  }


  deselectAll(select: NgModel) {
    select.update.emit([]);
  }


  warning() {
    this.toasterService.Warning("Warning!", "You aren't a student. You can't enter this page.");
  }

  success() {
    this.toasterService.Success("Success", "Your schedule was formed successfully");
  }

  error() {
    this.toasterService.Error("Error!", "Please choose time correctly");
  }

  onSubmit() {
    console.log(this.schedule);
    this.courseService.createSchedule(this.schedule).subscribe(
      value => {
        console.log('[POST] create schedule successfully', value);
      }, error => {
        console.log('FAIL to create schedule!');
        this.error();
      },
      () => {
        console.log('POST schedule - now completed.');
        this.success();
        this.router.navigate(['student/my-schedule']);
      });
  }

  public changeSuccessMessage() {
    this._success.next(`Your schedule saved successfully.`);
  }

}
