import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TrainersInfo} from "../../services/trainers-info";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TrainersserService {
  url = 'http://localhost:8080/api/trainerslist ';

  constructor(private httpClient : HttpClient) { }

  getInfo() : Observable<any>{
      return this.httpClient.get<TrainersInfo>(this.url, httpOptions);
  }
}
