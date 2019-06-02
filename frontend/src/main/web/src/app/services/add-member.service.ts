import { Injectable } from '@angular/core';
import {User} from "../interface/user";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Url} from "../urls/Url";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AddMemberService {
  constructor(private httpClient : HttpClient) { }

  public addMember(user : User) : Observable<any>{
    return this.httpClient.post(Url.ADD_MEMBER, user, httpOptions);
  }

}
