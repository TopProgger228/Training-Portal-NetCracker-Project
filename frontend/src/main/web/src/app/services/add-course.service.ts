import { Injectable } from '@angular/core';
import {Course} from "../interface/course";
import {HttpClient} from "@angular/common/http";
import {JwtResponse} from "../auth/jwt-response";
import {HttpHeaders} from "../../../node_modules/@angular/common/http";
import {Url} from "../urls/Url";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})


export class AddCourseService {
  constructor(private httpClient : HttpClient) { }

  public addCourse(course : Course){
    return this.httpClient.post<Course>(Url.CREATE_NEW_COURSE, course, httpOptions);
  }
}
