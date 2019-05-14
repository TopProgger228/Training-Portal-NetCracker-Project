import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ScheduleMod} from "./schedule-mod";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class ScheduleService {

  private scheduleUrl = 'http://localhost:8080/api/scheduleinfo';

  constructor(private http: HttpClient) { }

  getScheduleInfo(): Observable<any> {
    return this.http.get<ScheduleMod>(this.scheduleUrl, httpOptions);
  }
}
