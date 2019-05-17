import { Component, OnInit } from '@angular/core';
import {LessonAtt} from "../interface/lesson-att";
import {TrainerAttService} from "../services/trainer-att.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {NavigationExtras, Router} from "@angular/router";

@Component({
  selector: 'app-trainer-attendance',
  templateUrl: './trainer-attendance.component.html',
  styleUrls: ['./trainer-attendance.component.css']
})
export class TrainerAttendanceComponent implements OnInit {

  public lessonList: LessonAtt[];
  isLoading: boolean = true;

  constructor(private trainerService: TrainerAttService, private token: TokenStorageService, private router: Router) { }

  ngOnInit() {

    this.trainerService.getLessonsByUsername(this.token.getUsername())
      .subscribe(data => {this.lessonList = data; this.isLoading = false});

  }


  navigate(id: number) {
    let navigationExtras: NavigationExtras = {
      queryParams: {
        id: id
      }
    }
    this.router.navigate(['trainer/check-lesson-attendance'], navigationExtras);
  }
}
