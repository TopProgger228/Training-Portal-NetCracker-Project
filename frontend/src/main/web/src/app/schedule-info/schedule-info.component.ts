import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
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

  loggedOut = false;

  constructor(private router: Router, private scheduleService: ScheduleService, private token: TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Admin') {
          this.scheduleService.getScheduleInfo()
            .subscribe(data => {
              this.info = data;
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

  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}
