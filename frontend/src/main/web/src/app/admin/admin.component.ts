import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  user: string = "admin";

  loggedOut = false;

  constructor(private router: Router,private userService: UserService, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (!this.tokenStorage.getToken()) {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    }
  }
}
