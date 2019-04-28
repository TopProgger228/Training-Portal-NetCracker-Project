import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MailSenderService {

  private resetUrl = 'http://localhost:8080/api/resetPassword';

  constructor(private http: HttpClient) { }


  sendResetPasswordMail(email: string): Observable<any> {
    const req = new HttpRequest('POST', this.resetUrl , null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('email', email),
    });
    return this.http.request(req);
  }
}

