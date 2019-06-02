import { Component, OnInit } from '@angular/core';
import {ManagerAttService} from "../services/manager-att.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {ManagerUserAtt} from "../interface/manager-user-att";

@Component({
  selector: 'app-manager-attendance',
  templateUrl: './manager-attendance.component.html',
  styleUrls: ['./manager-attendance.component.css']
})
export class ManagerAttendanceComponent implements OnInit {

  managerUserList: ManagerUserAtt[] = [];
  statusList: any[] = [];
  isLoading: boolean = true;

  isDone: boolean;
  constructor(private managerAttService: ManagerAttService, private token: TokenStorageService) {
    this.managerAttService.getManagerUserByUsername(this.token.getUsername())
      .subscribe(data=> {this.managerUserList = data; this.init(); this.isLoading = false});
  }

  ngOnInit() {

  }

  init() {
    for (let i = 0; i < this.managerUserList.length ; i++) {
      this.statusList[i] = new Map<string, number>();
      Object.keys(this.managerUserList[i].lessonsMap).forEach(key => {
        this.addParameter(i, key, this.managerUserList[i].lessonsMap[key]);
      });
    }
  }


  addParameter(index:number, key: string, value: number) {
    this.statusList[index].set(key, value);
  }




}
