import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from './user-model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private trainersUrl = 'http://localhost:8080/api/usersinfo/trainers';
  private managersUrl = 'http://localhost:8080/api/usersinfo/managers';
  private studentsUrl = 'http://localhost:8080/api/usersinfo/students';
  private deleteUrl = 'http://localhost:8080/edit/delete?id=';
  private studentProfileUrl = 'http://localhost:8080/api/manager/student-profile?username=';


  constructor(private http: HttpClient) { }

  getTrainers(): Observable<any> {
    return this.http.get<UserModel>(this.trainersUrl, httpOptions);
  }
  getManagers(): Observable<any> {
    return this.http.get<UserModel>(this.managersUrl, httpOptions);
  }
  getStudents(): Observable<any> {
    return this.http.get<UserModel>(this.studentsUrl, httpOptions);
  }
  delete(id: number): Observable<any> {
    return this.http.delete(this.deleteUrl + id, httpOptions);
  }
  getStudentProfile(username: string): Observable<any> {
    return this.http.get<UserModel>(this.studentProfileUrl + username, httpOptions);
  }

}
