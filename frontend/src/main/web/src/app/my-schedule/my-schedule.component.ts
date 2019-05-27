import { Component, OnInit } from '@angular/core';
import { Schedule } from "../services/schedule";
import {ActivatedRoute, Router} from "@angular/router";
import { TokenStorageService } from "../auth/token-storage.service";
import {ToasterService} from "../services/toaster.service";
import {ScheduleService} from "../services/schedule.service";
import {StudentSchedule} from "../services/student-schedule";

@Component({
  selector: 'app-my-schedule',
  templateUrl: './my-schedule.component.html',
  styleUrls: ['./my-schedule.component.css']
})
export class MyScheduleComponent implements OnInit {

  username: string;
  studentSchedule: StudentSchedule[];

  loggedOut = false;

  constructor(private route: ActivatedRoute, private router: Router, private scheduleService: ScheduleService,
              private token: TokenStorageService, private toasterService: ToasterService) {
  }

  ngOnInit() {
    const _this = this;
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Student') {
          this.route.params.subscribe(params => {
            this.username = params['username'];
            console.log('Username', this.token.getUsername())
            // In a real app: dispatch action to load the details here.
          });


          this.scheduleService.getScheduleOfStudent(this.token.getUsername())
            .subscribe(data => {
              this.studentSchedule = data;
              console.log(this.studentSchedule);
            });
        } else {
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
