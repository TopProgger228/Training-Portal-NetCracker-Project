import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './auth/token-storage.service';

import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[];
  private authority: string;

  constructor(
    private router: Router,
    private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
     /* this.roles.every(role => {
        if (role === '1') {
          this.authority = 'admin';
          return false;
        } else if (role === '2') {
          this.authority = 'manager';
          return false;
        } else if (role === '3') {
          this.authority = 'trainer';
          return false;
        }
        this.authority = 'user';
        return true;
      });*/

      this.authority = 'auth/login';
    }
  }
}