import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';

import { UserService } from "../services/user.service";
import { UserModel } from "../services/user-model";


@Component({
  selector: 'app-users-info',
  templateUrl: './student-profile.html',
  styleUrls: ['./student-profile.css']
})
export class StudentProfileComponent implements OnInit {

  student: UserModel[];
  username: string;

  loggedOut = false;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, private token: TokenStorageService) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if ((role === 'Manager') || (role === 'Trainer')) {
          this.route.params.subscribe(params => {
            this.username = params['username']; // (+) converts string 'id' to a number

            // In a real app: dispatch action to load the details here.
          });
          this.userService.getStudentProfile(this.username)
            .subscribe(data => {
              this.student = data;
            })
        } else {
          this.router.navigate(['firstPage']);
        }
        return false;
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



