import { Component } from '@angular/core';
import { User } from "../services/user";
import { AddMemberService } from "../services/add-member.service";

import { Router } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-add-new-manager',
  templateUrl: './add-new-manager.component.html',
  styleUrls: ['./add-new-manager.component.css']
})
export class AddNewManagerComponent {
  roles = ['Trainer', 'Manager'];

  loggedOut = false;
  userModel = new User('', '', this.roles[0], '',
    '', '');

  constructor(private router: Router, private token: TokenStorageService, private httpService: AddMemberService) {

  }


  ngOnInit() {
    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role !== 'Admin') {
          this.router.navigate(['firstPage']);
        }
        return false;
      });
    } else {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    };
  };

  onSubmit() {
    console.log(this.userModel);
    this.httpService.addMember(this.userModel).subscribe(
      value => {
        console.log('[POST] create user successfully', value);
      }, error => {
        console.log('FAIL to create user!');
      },
      () => {
        console.log('POST user - now completed.');
      });
  }


  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }
}

