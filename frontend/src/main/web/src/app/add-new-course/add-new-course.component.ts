import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { TrainerService } from "../services/trainer.service";
import { TokenStorageService } from "../auth/token-storage.service";
import { Course } from "../services/course";
import { AddCourseService } from "../services/add-course.service";
import { Trainer } from "../services/trainer";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DateComparerValidation} from "../validators/date-compare-validation";
import {InvalidQuantityValidation} from "../validators/invalid-quantity-validation";

@Component({
  selector: 'app-add-new-course',
  templateUrl: './add-new-course.component.html',
  styleUrls: ['./add-new-course.component.css']
})

export class AddNewCourseComponent implements OnInit {
  loggedout = false;
  //course = new Course("", "", "", "", "",0,0);
  levels = ['Junior', 'Middle', 'Senior'];
  trainers: Trainer[];
  courseFormGroup: FormGroup;
  course: Course;


  constructor(private router: Router, private trainerService: TrainerService, private token: TokenStorageService,
              private httpService: AddCourseService, private formBuilder: FormBuilder) {
    this.courseFormGroup = this.formBuilder.group({
      name: ['', Validators.required],
      info: ['', Validators.required],
      skill_level: ['', Validators.required],
      trainer_id: ['', Validators.required],
      qty_per_week: ['', Validators.required],
      start_date: ['', Validators.required],
      end_date: ['', Validators.required]
    }, {
      validator: DateComparerValidation.validate.bind(this),
      validator: InvalidQuantityValidation.validate.bind(this)
    });
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Admin') {
          // this.courseFormGroup.setValidators(AddNewCourseComponent.qtyPerWeekValidator);
          // this.courseFormGroup.updateValueAndValidity();
          this.trainerService.getTrainers()
            .subscribe(data => {
              this.trainers = data;
            })
        } else {
          this.router.navigate(['firstPage']);
        }
        return false;
      });
    } else {
      this.loggedout = true;
      this.router.navigate(['auth/login']);
    }
  };

  submitted = false;

  onSubmit() {
    this.course = new Course(
      this.courseFormGroup.controls.name.value,
      this.courseFormGroup.controls.start_date.value,
      this.courseFormGroup.controls.end_date.value,
      this.courseFormGroup.controls.info.value,
      this.courseFormGroup.controls.skill_level.value,
      this.courseFormGroup.controls.trainer_id.value,
      this.courseFormGroup.controls.qty_per_week.value,

    );
    console.log(this.course);
    this.httpService.addCourse(this.course).subscribe(
      value => {
        console.log('[POST] create course successfully', value);
        this.router.navigate(['auth/login']);
      }, error => {
        console.log('FAIL to create course!');
      },
      () => {
        console.log('POST course - now completed.');
      });
  }

  compareFn(f1:any, f2:any):boolean{
    return f1.value == f2.value && f1.viewValue == f2.viewValue;
  }
}
