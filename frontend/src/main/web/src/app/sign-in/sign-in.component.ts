import { Component, OnInit } from '@angular/core';

import {ToasterService} from "../services/toaster.service";
import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { SignInInfo } from '../auth/signin-info';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})

export class SignInComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  roles: string[] = [];
  private loginInfo: SignInInfo;

  constructor(private authService: AuthService,
    private router: Router, 
    private tokenStorage: TokenStorageService,
    private toasterService: ToasterService) { }


  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.router.navigate(['firstPage']);

    }
  }

  onSubmit() {
    console.log(this.form);

    this.loginInfo = new SignInInfo(
      this.form.username,
      this.form.password); 

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoggedIn = true;


        this.router.navigate(['firstPage']);
      },
      error => {
        console.log(error);

        this.toasterService.Error("Fail!", error.error.message);
        this.form.password = null;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }
}
