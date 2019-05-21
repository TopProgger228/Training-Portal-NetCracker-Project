import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JwtResponse } from './jwt-response';
import { SignInInfo } from './signin-info';
import { SignUpInfo } from './signup-info';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private SignInUrl = 'http://localhost:8080/api/auth/signin';
  private SignUpUrl = 'http://localhost:8080/api/auth/signup';
  private ResetPasswordUrl = 'http://localhost:8080/api/auth/reset_password';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: SignInInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.SignInUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<any> {
    // const req = new HttpRequest('POST',this.SignUpUrl , info, {
    //   headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    //   reportProgress: true,
    //   responseType: 'text',
    //   params: new HttpParams().set("filename", info.photo.name)
    //     .set("fileExtension", info.photo.name.slice((info.photo.name.lastIndexOf(".") - 1 >>> 0) + 2)),
    // });
    // return this.http.request(req);
    return this.http.post<string>(this.SignUpUrl, info, httpOptions);
  }

  resetPassword(email: string, password: string): Observable<any> {
    const req = new HttpRequest('POST', this.ResetPasswordUrl, null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('email', email)
      .set("password", password),
    });
    return this.http.request<string>(req);
  }

}
