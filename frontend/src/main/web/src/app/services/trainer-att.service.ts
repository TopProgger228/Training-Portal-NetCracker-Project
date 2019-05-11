import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Class} from "../interface/class";
import {LessonAtt} from "../interface/lesson-att";
import {Course} from "./course";

@Injectable({
  providedIn: 'root'
})
export class TrainerAttService {

  // private all_lessons_url: string = "http://localhost:8080/api/trainerLesson/165";
  private all_lessons_url_by_username = "http://localhost:8080/api/getTrainerLesson/";
  private lesson_url: string = "http://localhost:8080/api/fullAttCheck/";
  private check_student_url: string = "http://localhost:8080/api/lessonAtt";

  constructor( private http: HttpClient) { }

  getLessonsByUsername(username: string): Observable<LessonAtt[]> {
    console.log(username);
    return this.http.get<LessonAtt[]>(this.all_lessons_url_by_username + username);
  }


  getOneLesson(id: number) {
    console.log(id);
    console.log(this.lesson_url + id.toString());
    return this.http.get<Class>(this.lesson_url + id);
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
