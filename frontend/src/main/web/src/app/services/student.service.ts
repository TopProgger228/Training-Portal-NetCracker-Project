import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "./student";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  url = 'http://localhost:8080/attendees/info?username=';
  infourl = 'http://localhost:8080/manager/students-info?username=';

  constructor(private httpClient : HttpClient) { }

  getStudents(username : string) : Observable<any>{
    return this.httpClient.get<Student>(this.url + username, httpOptions);
  }
  getStudentsOfManager(username : string) : Observable<any>{
    return this.httpClient.get<Student>(this.infourl + username, httpOptions);
  }
}
