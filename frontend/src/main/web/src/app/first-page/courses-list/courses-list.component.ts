import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorageService} from "../../auth/token-storage.service";
//import {Course} from "../../groups-schedule/course";
import {CourseService} from "../../services/course.service";

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit {

  roles: string[] = [];

  constructor(private courseService: CourseService,
              private router: Router,
              private tokenStorage: TokenStorageService) { }

  ngOnInit() { }


}
