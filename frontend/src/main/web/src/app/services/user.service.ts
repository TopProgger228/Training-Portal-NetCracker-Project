import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from '../interface/user-model';
import {HttpParams, HttpRequest} from "../../../node_modules/@angular/common/http";

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
  private userProfileUrl = 'http://localhost:8080/api/getUser?username=';
  private updateUserUrl = 'http://localhost:8080/api/updateUser';
  private getPhotoUrl = 'http://localhost:8080/api/getPhoto';
  private postPhotoUrl = 'http://localhost:8080/api/postPhoto';
  private trainerUrl = 'http://localhost:8080/api/gettrainer';


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
  getUserProfile(username: string): Observable<any> {
    return this.http.get<UserModel>(this.userProfileUrl + username, httpOptions);
  }
  getTrainerOfStudent(id: number): Observable<any> {
    const req = new HttpRequest('GET', this.trainerUrl, null, {
      params: new HttpParams().set('id', id.toString()),
    });
    return this.http.request(req);
  }
  updateUser(id, username, fname, lname, email): Observable<any> {
    const updateReq = new HttpRequest('POST', this.updateUserUrl , null, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('id', id)
        .set('username', username)
        .set('fname', fname)
        .set('lname', lname)
        .set('email', email),
    });
    return this.http.request(updateReq);
  }
  uploadPhoto(username: string, photo: File): Observable<any>{
    console.log(photo);
    const req = new HttpRequest('POST', this.postPhotoUrl, photo, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('username', username)
        .set("filename", photo.name)
        .set("fileExtension", photo.name.slice((photo.name.lastIndexOf(".") - 1 >>> 0) + 2)),
    });
    console.log(req);

    return this.http.request<string>(req)
  }
  getPhoto(username: string): Observable<any> {
    const updateReq = new HttpRequest('GET', this.getPhotoUrl , null, {
      params: new HttpParams().set('username', username),
      responseType: 'text'
    });
    return this.http.request(updateReq);
  }
}
