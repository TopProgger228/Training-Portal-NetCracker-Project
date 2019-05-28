import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trainer } from './trainer';
import {Lesson} from "../interface/lesson";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TrainerService {

  private trainersUrl = 'http://localhost:8080/api/gettrainers';
  private get_trainer_lessons_by_group_id_url = "http://localhost:8080/api/getLessonByCourseId/";

  constructor(private http: HttpClient) { }

  getTrainers(): Observable<any> {
    return this.http.get<Trainer>(this.trainersUrl, httpOptions);
  }

  getLessonByCourse(courseId: number): Observable<Lesson[]> {
    return this.http.get<Lesson[]>(this.get_trainer_lessons_by_group_id_url + courseId, httpOptions)
  }


}
