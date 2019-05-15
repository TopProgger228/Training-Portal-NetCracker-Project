import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ScheduleService} from "../services/schedule.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {ScheduleMod} from "../services/schedule-mod";

@Component({
  selector: 'app-schedule-info',
  templateUrl: './schedule-info.component.html',
  styleUrls: ['./schedule-info.component.css']
})
export class ScheduleInfoComponent implements OnInit {

  info: ScheduleMod[];
  name: string;
  loggedOut = false;


  constructor(private router: Router, private scheduleService: ScheduleService, private token: TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Admin') {
          this.scheduleService.getScheduleInfo()
            .subscribe(data => {
              this.info = data;
              console.log('This record = ', this.info)
            })
        }else {
          this.router.navigate(['firstPage']);
        }
        return false;
      });
    } else {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    };
  };

  OnSubmitSchedule(id: number){
    /*console.log('Local record = ', this.info);
    let result = this.info.map(({ courseId }) => courseId);
    console.log('Current id1 = ', result);*/
    this.scheduleService.isChoosen(id).subscribe(data => {
      console.log(data);
    });
    this.reloadPage();
  }

  reloadPage() {
    window.location.reload();
  }

  checkboxChanged(event) {
    console.log(event.checked);
  }

  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}
