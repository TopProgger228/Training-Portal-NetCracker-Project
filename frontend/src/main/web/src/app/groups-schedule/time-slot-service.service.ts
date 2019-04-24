import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Timeslot} from "./timeslot";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TimeSlotServiceService {

  private timeslotsUrl = 'http://localhost:8080/api/timeslot';

  constructor(private http: HttpClient) { }

  getTimeSlots(): Observable<any> {
    return this.http.get<Timeslot>(this.timeslotsUrl, httpOptions);
  }
}
