import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ScheduleService} from "../services/schedule.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {ScheduleMod} from "../interface/schedule-mod";
import {ToasterService} from "../services/toaster.service";

@Component({
  selector: 'app-schedule-info',
  templateUrl: './schedule-info.component.html',
  styleUrls: ['./schedule-info.component.css']
})
export class ScheduleInfoComponent implements OnInit, OnChanges {

  @Input('isScheduled')
  info: ScheduleMod[];

  name: string;
  loggedOut = false;
  myCountVoted: any[];
  constructor(private router: Router, private scheduleService: ScheduleService, private token: TokenStorageService,
              private toasterService: ToasterService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Admin') {
          this.scheduleService.getScheduleInfo()
            .subscribe(data => {
              this.info = data;
              console.log('This record = ', this.info)
              this.myCountVoted = this.info.map(({countVoted}) => countVoted);
              console.log('CountVotedArray = ', this.myCountVoted);
            })
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

  ngOnChanges(changes: SimpleChanges) {
    console.log('Alert from child component, on change in parent component.');
    // console.log(changes);
    // const isScheduledValue = changes['isScheduled'];
    // for (let schedule of this.info) {
    //   if (isScheduledValue.currentValue === 'Schedule has been set') {
    //     schedule.isScheduled == 'Schedule has been set';
    //   }
    //   else {
    //     schedule.isScheduled == 'Not set yet';
    //   }
    // }
  }

  success1() {
    this.toasterService.Success("Success", "You finished creating schedule for students");
  }

  success2() {
    this.toasterService.Success("Success", "You created lesson successfully");
  }

  error2() {
    this.toasterService.Error("Error", "Fail to create lesson");
  }

  OnSubmitSchedule(id: number) {
    this.scheduleService.isChoosen(id).subscribe(data => {
      console.log(data);
      }, error => {
        console.log('FAIL to finish schedule!');
      },
      () => {
        console.log('isChoosen == true.');
        this.scheduleService.getScheduleInfo()
          .subscribe(data => {
            this.info = data;
            console.log('This record = ', this.info)
          })
        this.success1()
        //this.reloadPage();
        setTimeout(() => {
            this.router.navigate(['admin/all-attendance']);
          },6000);
      });
  }


/*
  OnSubmitLesson(id: number){
    this.scheduleService.createLesson(id).subscribe(data => {
        console.log(data);
      }, error => {
        console.log('FAIL to finish lesson!');
      },
      () => {
        console.log('isChoosen == true.');
        this.success2()
        //this.reloadPage();
      });
  }
*/
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
