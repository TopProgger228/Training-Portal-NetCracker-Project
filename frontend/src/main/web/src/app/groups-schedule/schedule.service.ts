import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Schedule} from "./schedule";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  private studyUrl = 'http://localhost:8080/api/create_new_schedule';

  constructor(private httpClient : HttpClient) { }

  createSchedule(scheduler: Schedule): Observable<any> {
    return this.httpClient.post<Schedule>(this.studyUrl, scheduler,httpOptions);
  }
}
