import {Component, Input, OnInit} from '@angular/core';
import {UserAtt} from "../../interface/user-att";

@Component({
  selector: 'app-one-student-admin-att',
  templateUrl: './one-student-admin-att.component.html',
  styleUrls: ['./one-student-admin-att.component.css']
})
export class OneStudentAdminAttComponent implements OnInit {

  @Input('userList') public userList: UserAtt[];

  constructor() { }

  ngOnInit() {
  }

}
