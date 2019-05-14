import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trainer } from './trainer';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TrainerService {

  private trainersUrl = 'http://localhost:8080/api/gettrainers';

  constructor(private http: HttpClient) { }

  getTrainers(): Observable<any> {
    return this.http.get<Trainer>(this.trainersUrl, httpOptions);
  }




}
