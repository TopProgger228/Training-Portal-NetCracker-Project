import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MailSenderService {

  private resetUrl = 'http://localhost:8080/api/signup/send_email';
  private getEmailByTokenURL = 'http://localhost:8080/api/signup/getEmailByToken';

  constructor(private http: HttpClient) { }


  sendSignUpMail(email: string): Observable<any> {
    const req = new HttpRequest('POST', this.resetUrl, null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('email', email),
    });
    return this.http.request(req);
  }

  getEmailByToken(token: string): Observable<any> {
    const req = new HttpRequest('POST', this.getEmailByTokenURL , null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('token', token)
    });
    return this.http.request(req);
  }



}

