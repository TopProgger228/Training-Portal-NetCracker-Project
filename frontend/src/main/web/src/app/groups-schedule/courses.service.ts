import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Courses} from "./courses";
import {Course} from "../interface/course";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private coursesUrl = 'http://localhost:8080/api/courses-list';

  constructor(private http: HttpClient) { }

  getCourses(): Observable<any> {
    return this.http.get<Course>(this.coursesUrl, httpOptions);
  }
}
