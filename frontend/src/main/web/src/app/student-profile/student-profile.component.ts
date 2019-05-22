import { Component, OnInit } from '@angular/core';

import {ActivatedRoute, Router} from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';

import {UserService} from "../services/user.service";
import {UserModel} from "../services/user-model";
import {DomSanitizer} from "@angular/platform-browser";


@Component({
  selector: 'app-student-profile',
  templateUrl: './student-profile.html',
  styleUrls: ['./student-profile.css']
})
export class StudentProfileComponent implements OnInit {

  student: UserModel;
  username: string;
  hasPhoto = false;

  loggedOut = false;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private token: TokenStorageService, private sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if ((role === 'Manager') || (role === 'Trainer')) {
          this.route.params.subscribe(params => {
            this.username = params['username'];
          });
          this.userService.getUserProfile(this.username)
            .subscribe(data => {
              this.student = data;
              if(data.photo != null) {
                this.student.photo = this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + data.photo);
                this.hasPhoto=true;
              }
              console.log(this.student);
            })
        } else {
          this.router.navigate(['firstPage']);
        }
        return true;
      });
    } else {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    };
  };

  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }


}



