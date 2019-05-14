import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest, HttpParams} from "@angular/common/http";
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
  private updateTrainerUrl = 'http://localhost:8080/api/updateTrainer';

  constructor(private httpClient : HttpClient) { }

  getInfo() : Observable<any>{
      return this.httpClient.get<TrainersInfo>(this.url, httpOptions);
  }

  updateTrainerInfo(trainer: TrainersInfo): Observable<any>{
    console.log(trainer);   
     const req = new HttpRequest('POST', this.updateTrainerUrl , null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('id', trainer.id.toString())
      .set('fname', trainer.fname)
      .set('lname', trainer.lname)
      .set('info', trainer.info),
    });
    return this.httpClient.request(req);
   }
}
