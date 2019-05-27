import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ScheduleMod} from "./schedule-mod";
import {Schedule} from "./schedule";

const httpOptions = {

  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})

export class ScheduleService {

  private scheduleUrl = 'http://localhost:8080/api/scheduleinfo';
  private courseIdUrl = "http://localhost:8080/api/course_id?name=";
  private isChoosenUrl = 'http://localhost:8080/api/is_choosen?id=';
  private myScheduleUrl = "http://localhost:8080/api/student/my-schedule?username=";

  constructor(private http: HttpClient) { }

  getScheduleInfo(): Observable<any> {
    return this.http.get<ScheduleMod>(this.scheduleUrl, httpOptions);
  }

  getScheduleOfStudent(username: string): Observable<any>{
    return this.http.get<number>(this.myScheduleUrl + username, httpOptions);
  }

  isChoosen(id: number): Observable<any> {
    return this.http.get<Schedule>(this.isChoosenUrl + id, httpOptions);
  }

}
