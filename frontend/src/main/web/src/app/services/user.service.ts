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

}
