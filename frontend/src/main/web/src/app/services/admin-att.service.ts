import { Injectable } from '@angular/core';
import {UserAtt} from "../interface/user-att";
import {LessonAtt} from "../interface/lesson-att";
import {CourseAtt} from "../interface/course-att";
import {HttpClient} from "@angular/common/http";
import {TrainerSelector} from "../interface/trainer-selector";

@Injectable({
  providedIn: 'root'
})
export class AdminAttService {

  private course_url: string = "http://localhost:8080/api/attCourse"
  private lesson_url: string = "http://localhost:8080/api/attLesson/"
  private user_url: string = "http://localhost:8080/api/attUser/"
  private filter_by_use_url: string = "http://localhost:8080/api/filterCourseByUser/"
  private filter_by_trainer_url: string = "http://localhost:8080/api/filterCourseByTrainer/"
  private filter_by_level_url: string = "http://localhost:8080/api/filterCourseBySkillLevel/"
  private trainer_url: string = "http://localhost:8080/api/getAllTrainer"



  constructor( private http: HttpClient) { }

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

  filterByTrainer(id: number) {
    return this.http.get<CourseAtt[]>(this.filter_by_trainer_url + id)
  }

  filterBySkillLevel(level: string) {
    return this.http.get<CourseAtt[]>(this.filter_by_level_url + level)
  }

  getAllTrainer() {
    return this.http.get<TrainerSelector[]>(this.trainer_url)
  }

}
