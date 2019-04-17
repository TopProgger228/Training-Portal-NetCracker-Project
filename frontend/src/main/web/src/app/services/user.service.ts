import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost:8080/api/test/user';
  private trainerUrl = 'http://localhost:8080/api/test/trainer';
  private adminUrl = 'http://localhost:8080/api/test/admin';
  private mngUrl = 'http://localhost:8080/api/test/admin';

  constructor(private http: HttpClient) { }

  getUserBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }

  getTrainerBoard(): Observable<string> {
    return this.http.get(this.trainerUrl, { responseType: 'text' });
  }

  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }

  getMngBoard(): Observable<string> {
    return this.http.get(this.mngUrl, { responseType: 'text' });
  }
}
