import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Manager} from "./manager";
import {Student} from "./student";
import {UserModel} from "./user-model";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ManagerService {


  url = 'http://localhost:8080/api/manager/profile?username=';

  constructor(private httpClient : HttpClient) { }

  getManagerOfStudent(username : string) : Observable<any>{
    return this.httpClient.get<Manager>(this.url + username, httpOptions);
  }
}