import { Component, OnInit, Input, ViewChild } from '@angular/core';

import { TokenStorageService } from '../../auth/token-storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})

export class MenuComponent implements OnInit {
  
  role: string;
  roles: string[];

  info: any;
  constructor(private token: TokenStorageService,
    private router: Router) { }

  ngOnInit() {

    if (this.token.getToken()) {
      this.roles = this.token.getAuthorities();
      this.roles.every(role => {
        if (role === 'Admin') {
          this.role = 'admin';
          return false;
        } else if (role === 'Manager') {
          this.role = 'mng';
          return false;
        } else if (role === 'Trainer') {
          this.role='trainer';
          return false;
        }else if (role === 'Student') {
          this.role= 'user';
          return false;
        }
        return true;
      });
    }else{

      this.router.navigate(['auth/login']);
    }

  }
  logout() {
    this.token.signOut();
    this.router.navigate(['']);
    window.location.reload();
  }

}
