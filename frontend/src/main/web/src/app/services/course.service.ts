import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Courses} from "../groups-schedule/courses";
import {Observable} from "rxjs";
import {Course} from "./course";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private createCourseUrl = 'http://localhost:8080/api/create_new_course';
  private coursesUrl = 'http://localhost:8080/api/coursesinfo/getcourses?username=';
  private courseListUrl = "http://localhost:8080/api/courses-list"
  private courseLastTenListUrl = "http://localhost:8080/api/last-ten-courses-list";

  constructor(private http: HttpClient) {
  }

  createNewCourse(newCourse: Courses): Observable<any> {
    return this.http.post<string>(this.createCourseUrl, newCourse, httpOptions);
  }

  getCourses(username: string): Observable<any> {
    return this.http.get<Course>(this.coursesUrl + username, httpOptions);
  }

  getCoursesList(): Observable<any> {
    return this.http.get<Course>(this.courseListUrl, httpOptions);
  }

  getLastTenCoursesList(): Observable<any> {
    return this.http.get<Course>(this.courseLastTenListUrl, httpOptions);
  }
}
