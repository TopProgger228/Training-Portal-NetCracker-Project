import { Injectable } from '@angular/core';
import {Course} from "./course";
import {HttpClient} from "@angular/common/http";
import {JwtResponse} from "../auth/jwt-response";
import {HttpHeaders} from "../../../node_modules/@angular/common/http";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})


export class AddCourseService {
  addUrl = 'http://localhost:8080/api/create_new_course';

  constructor(private httpClient : HttpClient) { }

  public addCourse(course : Course){
    return this.httpClient.post<Course>(this.addUrl, course, httpOptions);
  }
}
