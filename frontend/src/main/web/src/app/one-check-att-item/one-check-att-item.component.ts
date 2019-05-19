import { Component, OnInit, Input } from '@angular/core';
import {UserAtt} from "../interface/user-att";
import {TrainerAttService} from "../services/trainer-att.service";
import {ToasterService} from "../services/toaster.service";

@Component({
  selector: 'app-one-check-att-item',
  templateUrl: './one-check-att-item.component.html',
  styleUrls: ['./one-check-att-item.component.css']
})
export class OneCheckAttItemComponent implements OnInit {

  @Input('userAtt') public user: UserAtt;
  @Input('index') public index: number;
  @Input('lessonId') public lessonId;
  statusList: string[] = [
    "Present",
    "Absent due to business trip",
    "Absent without reason",
    "Absent due to sick leave",
    "Absent due to project activities"
  ]

  selectedStatus:  string = "Present";

  constructor(private trainerService: TrainerAttService, private toasterService: ToasterService) { }

  ngOnInit() {
  }

  putAttendanceStatus (userId: string, lessonId: string, status: string) {
    this.trainerService.putAttendanceStatus(userId, lessonId, status)
      .subscribe(()=>{},
        () =>{
          this.error(this.user.userFirstName, this.user.userLastName)
        },
        ()=>{
          this.success(this.user.userFirstName, this.user.userLastName);
        });
  }

  check(userId: string, lessonId: string, status: string) {
    this.putAttendanceStatus(userId,lessonId, status);
  }

  success(firstName: string, lastName: string) {
    this.toasterService.Success("Check",firstName + " " + lastName + " is checked!");
  }

  info() {
    this.toasterService.Info("Success");
  }

  error(firstName: string, lastName: string) {
    this.toasterService.Error("Error!",firstName + " " + lastName + " is not checked");
  }

}
