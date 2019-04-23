import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {StudySchedule} from "./study-schedule";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  private studyUrl = 'http://localhost:8080/api/create_new_studySchedule';

  constructor(private http: HttpClient) { }

  createStudySchedule(studySceduler: StudySchedule): Observable<any> {
    return this.http.post<StudySchedule>(this.studyUrl, studySceduler,httpOptions);
  }
}
