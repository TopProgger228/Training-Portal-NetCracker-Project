import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Timeslots} from "./timeslots";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Timeslot} from "./timeslot";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class GettimeslotService {

  private timeslotsUrl = 'http://localhost:8080/api/timeslot';

  constructor(private httpClient : HttpClient) { }

  getTimeSlots(): Observable<any> {
    return this.httpClient.get<Timeslots>(this.timeslotsUrl, httpOptions);
  }
}
