import { Component, OnInit } from '@angular/core';
import {Timeslot} from "./timeslot";
import {Router} from "@angular/router";
import {TokenStorageService} from "../auth/token-storage.service";
import {TimeSlotServiceService} from "./time-slot-service.service";

@Component({
  selector: 'app-groups-schedule',
  templateUrl: './groups-schedule.component.html',
  styleUrls: ['./groups-schedule.component.css']
})
export class GroupsScheduleComponent implements OnInit {

  timeSlots: Timeslot[];

  loggedout = false;

  constructor(private router: Router, private timeSlotService: TimeSlotServiceService, private token: TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.timeSlotService.getTimeSlots()
        .subscribe(data => {
          this.timeSlots = data;
        })
    }else {
      this.loggedout = true;
      this.router.navigate(['auth/login']);
    };
  };

  logout() {
    this.loggedout = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

}
