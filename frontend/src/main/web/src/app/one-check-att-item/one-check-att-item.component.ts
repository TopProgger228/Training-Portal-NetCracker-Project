import { Component, OnInit, Input } from '@angular/core';
import {UserAtt} from "../interface/user-att";
import {TrainerAttService} from "../services/trainer-att.service";

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

  constructor(private trainerService: TrainerAttService) { }

  ngOnInit() {
  }

  putAttendanceStatus (userId: string, lessonId: string, status: string) {
    this.trainerService.putAttendanceStatus(userId, lessonId, status)
      .subscribe(data=>{
        console.log(data);
      });
  }

}
