import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import {ɵBROWSER_SANITIZATION_PROVIDERS, DomSanitizer} from '@angular/platform-browser';
import { TokenStorageService } from '../auth/token-storage.service';

import {UserService} from "../services/user.service";
import {UserModel} from "../interface/user-model";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: UserModel;
  image:any;
  selectedFile: File;
  hasPhoto = false;
  loggedOut = false;
  EditProfile: boolean = false;

  constructor(private router: Router, private userService: UserService, private token: TokenStorageService, private sanitizer: DomSanitizer) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.userService.getUserProfile(this.token.getUsername())
        .subscribe(data => {
          this.user = data;
          if(data.photo != null) {
            this.user.photo = this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + data.photo);
            this.hasPhoto=true;
          }
        })
    } else {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    }
  }

  updateUser(user: UserModel) {
    console.log(user);
    this.userService.updateUser(user.id, user.username, user.fname, user.lname, user.email).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
    this.EditProfile = false;

  }

  setEditUser() {
    this.EditProfile = true;
  }

  uploadPhoto(){
    console.log(this.selectedFile);
    this.userService.uploadPhoto(this.user.username, this.selectedFile).subscribe(
          data => {
            console.log(data);
           // window.location.reload();
          },
          error => {
            console.log(error);
            //window.location.reload();
          }
       );
  }

  onFileChanged(event) {
    this.selectedFile = event.target.files.item(0);
  }

  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }


}
