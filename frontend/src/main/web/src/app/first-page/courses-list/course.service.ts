import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Courses} from "../../groups-schedule/courses";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private createCourseUrl = 'http://localhost:8080/api/create_new_course';

  constructor(private http: HttpClient) {
  }

  createNewCourse(newCourse: Courses): Observable<any> {
    return this.http.post<string>(this.createCourseUrl, newCourse, httpOptions);
  }
}
