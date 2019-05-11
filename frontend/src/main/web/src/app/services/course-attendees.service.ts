import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CourseAttendeesService {
  name : string;

  attendeesUrl = 'http://localhost:8080/api/coursesinfo/getcourses/getcoursesattendee?name=';

  constructor(private httpClient : HttpClient) { }

  getCourseAttendees(course : string) : Observable<any>{
    return this.httpClient.get(this.attendeesUrl + course, httpOptions);
  }
}
