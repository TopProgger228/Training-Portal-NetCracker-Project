import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Timeslot} from "./timeslot";
import {Timeslots} from "./timeslots";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TimeSlotService {

  private createTimeSlotsUrl = 'http://localhost:8080/api/create_new_timeslot';

  private timeslotsUrl = 'http://localhost:8080/api/timeslot';

  constructor(private httpClient : HttpClient) { }

  public addTimeslot(timeslot : Timeslot){
    return this.httpClient.post<Timeslot>(this.createTimeSlotsUrl, timeslot, httpOptions);
  }
  getTimeSlots(): Observable<any> {
    return this.httpClient.get<Timeslot>(this.timeslotsUrl, httpOptions);
  }

}
