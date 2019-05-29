import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Courses} from "../groups-schedule/courses";
import {Observable} from "rxjs";
import {Course} from "./course";
import {Timeslot} from "../groups-schedule/timeslot";
import {Schedules} from "./schedules";
import {HttpParams, HttpRequest} from "../../../node_modules/@angular/common/http";
import {CourseForTrainer} from "../interface/course-for-trainer";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private createCourseUrl = 'http://localhost:8080/api/create_new_course';
  private coursesUrl = 'http://localhost:8080/api/coursesinfo/getcourses?username=';
  private courseListUrl = "http://localhost:8080/api/courses-list";
  private courseActiveListUrl = "http://localhost:8080/api/active-courses-list";
  private coursePageUrl = "http://localhost:8080/api/student/course-page?name=";
  private studyUrl = 'http://localhost:8080/api/create_new_schedule';
  private timeUrl = "http://localhost:8080/api/timeslot?name=";
  private userIdUrl = "http://localhost:8080/api/student_id?username=";
  private studentCoursesUrl = "http://localhost:8080/api/studentCourses";
  private get_course_by_trainer_username_url = "http://localhost:8080/api/getCoursesByTrainer/";

  constructor(private http: HttpClient) {
  }

  createNewCourse(newCourse: Courses): Observable<any> {
    return this.http.post<string>(this.createCourseUrl, newCourse, httpOptions);
  }

  getCourses(username: string): Observable<any> {
    return this.http.get<Course>(this.coursesUrl + username, httpOptions);
  }

  getCoursesList(): Observable<any> {
    return this.http.get<Course>(this.courseListUrl, httpOptions);
  }

  getActiveCoursesList(): Observable<any> {
    return this.http.get<Course>(this.courseActiveListUrl, httpOptions);
  }

  getCoursePage(name: string): Observable<any>{
    return this.http.get<Course>(this.coursePageUrl + name, httpOptions);
  }

  getTimeSlots(name: string): Observable<any> {
    return this.http.get<Timeslot>(this.timeUrl + name, httpOptions);
  }

  createSchedule(scheduler: Schedules): Observable<any> {
    return this.http.post<Schedules>(this.studyUrl, scheduler,httpOptions);
  }

  getIdByUsername(username: string): Observable<any>{
    return this.http.get<number>(this.userIdUrl + username, httpOptions);
  }

  getStudentCoursesByUsername(username: string): Observable<any>{
    const req = new HttpRequest('GET', this.studentCoursesUrl , null, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      params: new HttpParams().set('username', username),
    });
    return this.http.request(req);
  }

  getCoursesForTrainerByTrainerUsername(username: string): Observable<CourseForTrainer[]> {
    return this.http.get<CourseForTrainer[]>(this.get_course_by_trainer_username_url + username, httpOptions)
  }
}
