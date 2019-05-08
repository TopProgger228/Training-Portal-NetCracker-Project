import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Class} from "../interface/class";
import {LessonAtt} from "../interface/lesson-att";

@Injectable({
  providedIn: 'root'
})
export class TrainerAttService {

  private all_lessons_url: string = "http://localhost:8080/api/trainerLesson/165";
  private lesson_url: string = "http://localhost:8080/api/fullAttCheck/6";
  private check_student_url: string = "http://localhost:8080/api/lessonAtt";

  constructor( private http: HttpClient) { }

  getLessons() {
    return this.http.get<LessonAtt[]>(this.all_lessons_url);
  }

  getOneLesson() {
    return this.http.get<Class>(this.lesson_url);
  }

  putAttendanceStatus(userId: string, lessonId: string, status: string): Observable<any> {

    const updatereq = new HttpRequest('POST', this.check_student_url , null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('userId', userId)
        .set('lessonId', lessonId)
        .set('status', status)
    });
    return this.http.request(updatereq);
  }
}
