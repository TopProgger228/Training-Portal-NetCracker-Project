import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from './user-model';
import {Course} from "./course";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private coursesUrl = 'http://localhost:8080/api/coursesinfo/getcourses?username=';

  constructor(private http: HttpClient) { }

  getCourses(username: string): Observable<any> {
    return this.http.get<Course>(this.coursesUrl + username, httpOptions);
  }

}
