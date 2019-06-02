import { Injectable } from '@angular/core';
import { UserAtt } from "../interface/user-att";
import { LessonAtt } from "../interface/lesson-att";
import { CourseAtt } from "../interface/course-att";
import { HttpClient, HttpRequest, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class AdminAttService {

  private course_url: string = "http://localhost:8080/api/attCourse";
  private lesson_url: string = "http://localhost:8080/api/attLesson/";
  private user_url: string = "http://localhost:8080/api/attUser/";
  private filter_by_use_url: string = "http://localhost:8080/api/filterCourseByUser/";
  private filter_by_trainer_username_url: string = "http://localhost:8080/api/filterCourseByTrainerUsername/";
  private filter_by_level_url: string = "http://localhost:8080/api/filterCourseBySkillLevel/";
  private create_report_by_course_url: string = "http://localhost:8080/api/createReport/course";
  private create_report_by_trainer_username_url: string = "http://localhost:8080/api/createReport/trainer";
  private create_report_by_student_url: string = "http://localhost:8080/api/createReport/student";
  private create_report_by_level_url: string = "http://localhost:8080/api/createReport/level";



  constructor(private http: HttpClient) { }

  getCourses() {
    return this.http.get<CourseAtt[]>(this.course_url);
  }

  getLessons(id: number) {
    return this.http.get<LessonAtt[]>(this.lesson_url + id);
  }

  getStudents(id: number) {
    return this.http.get<UserAtt[]>(this.user_url + id)
  }

  filterByUser(username: string) {
    return this.http.get<CourseAtt[]>(this.filter_by_use_url + username)
  }

  filterByTrainerUsername(username: string) {
    return this.http.get<CourseAtt[]>(this.filter_by_trainer_username_url + username)
  }

  filterBySkillLevel(level: string) {
    return this.http.get<CourseAtt[]>(this.filter_by_level_url + level)
  }


  createReportByCourse(courses: number[]): Observable<any> {
    let params = new HttpParams();

    courses.forEach((course: number) => {
      params = params.append('courses', course.toString());
    })
    const req = new HttpRequest('POST', this.create_report_by_course_url, null, {
      reportProgress: true,
      responseType: 'text',
      params: params,
    });
    return this.http.request(req);
  }

  createReportByTrainer(trainerUsername: string): Observable<any> {
    const req = new HttpRequest('POST', this.create_report_by_trainer_username_url, null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams()
        .set('trainerUsername', trainerUsername)
    });
    return this.http.request(req);
  }

  createReportByStudent(username: string): Observable<any> {
    const req = new HttpRequest('POST', this.create_report_by_student_url, null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams()
        .set('username', username)
    });
    return this.http.request(req);
  }

  createReportByLevel(level: string): Observable<any> {
    console.log(level);
    const req = new HttpRequest('POST', this.create_report_by_level_url, null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams()
        .set('level', level)
    });
    return this.http.request(req);
  }

}
