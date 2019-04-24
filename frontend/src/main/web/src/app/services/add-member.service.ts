import { Injectable } from '@angular/core';
import {User} from "./user";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AddMemberService {
  addUrl = 'http://localhost:8080/edit/addmember';

  constructor(private httpClient : HttpClient) { }

  public addMember(user : User){
    return this.httpClient.post(this.addUrl, user);
  }
}
