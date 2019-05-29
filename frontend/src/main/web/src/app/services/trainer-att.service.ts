import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Class} from "../interface/class";
import {LessonAtt} from "../interface/lesson-att";
import {UserAtt} from "../interface/user-att";

@Injectable({
  providedIn: 'root'
})
export class TrainerAttService {

  private all_lessons_url_by_username = "http://localhost:8080/api/getTrainerLesson/";
  private lesson_url: string = "http://localhost:8080/api/fullAttCheck/";
  private get_students_by_lesson = "http://localhost:8080/api/getStudentsByLesson/";
  private check_student_url: string = "http://localhost:8080/api/lessonAtt";

  constructor( private http: HttpClient) { }

  getLessonsByUsername(username: string): Observable<LessonAtt[]> {
    return this.http.get<LessonAtt[]>(this.all_lessons_url_by_username + username);
  }


  getOneLesson(id: number) {
    return this.http.get<Class>(this.lesson_url + id);
  }

  getStudentsByLesson(id: number) {
    return this.http.get<UserAtt[]>(this.get_students_by_lesson + id);
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
