import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: SignInInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.SignInUrl, credentials, httpOptions);
  }

    signUp(info: SignUpInfo): Observable<string> { 
    return this.http.post<string>(this.SignUpUrl, info, httpOptions);
  }
}
