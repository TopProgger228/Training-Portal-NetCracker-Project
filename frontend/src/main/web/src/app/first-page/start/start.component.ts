import { Component, OnInit, Input } from '@angular/core';
import { TokenStorageService } from '../../auth/token-storage.service';

import { Router } from '@angular/router';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})

export class StartComponent implements OnInit {
  info: any;
  @Input()
  role: string;
  loggedout = false;
  constructor( private router: Router, private token: TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.info = {
        token: this.token.getToken(),
        username: this.token.getUsername(),
        authorities: this.token.getAuthorities()
      }
    }else{
      this.loggedout = true;
      this.router.navigate(['auth/login']);
    };
  }

  logout() {
    this.loggedout = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }
}
