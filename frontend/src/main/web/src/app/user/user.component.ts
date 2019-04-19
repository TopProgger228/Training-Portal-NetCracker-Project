import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: string = "user";

  loggedOut = false;

  constructor(private router: Router,private userService: UserService, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (!this.tokenStorage.getToken()) {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    }
  }
}
