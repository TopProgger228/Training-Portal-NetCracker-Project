import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ManagerUserAtt} from "../interface/manager-user-att";
import {CourseAtt} from "../interface/course-att";
import {LessonAtt} from "../interface/lesson-att";

@Injectable({
  providedIn: 'root'
})
export class ManagerAttService {

  private get_manager_user_att: string = "http://localhost:8080/api/getManagerUserAtt/";
  private get_course_by_user_id: string = "http://localhost:8080/api/getCourseByUserId/";
  private get_lessons_for_one_user: string = "http://localhost:8080/api/getCourseLessonsForOneUser/"

  constructor( private http: HttpClient) { }

  getManagerUserByUsername(username: string): Observable<ManagerUserAtt[]> {
    return this.http.get<ManagerUserAtt[]>(this.get_manager_user_att + username);
  }

  getCourseByUserId(userId: number): Observable<CourseAtt[]> {
    return this.http.get<CourseAtt[]>(this.get_course_by_user_id + userId);
  }

  getLessonsForOneUser(courseId:number, userId: number): Observable<LessonAtt[]> {
    console.log(this.get_lessons_for_one_user + courseId + "/" + userId)
    return this.http.get<LessonAtt[]>(this.get_lessons_for_one_user + courseId + "/" + userId);
  }
}
