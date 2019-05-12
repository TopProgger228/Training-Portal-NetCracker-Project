import { Injectable } from '@angular/core';
import {User} from "./user";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AddMemberService {
  addUrl = 'http://localhost:8080/edit/addmember';

  constructor(private httpClient : HttpClient) { }

  public addMember(user : User) : Observable<any>{
    return this.httpClient.post(this.addUrl , user, httpOptions);
  }

}
