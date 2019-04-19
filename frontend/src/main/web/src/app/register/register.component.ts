import { Component, OnInit } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';

import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;

  roles: string[] = [];
  errorMessage = '';

  constructor(private authService: AuthService,
    private router: Router,
    private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isSignedUp = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === '1') {
          this.router.navigate(['admin']);
          return false;
        } else if (role === '2') {
          this.router.navigate(['mng']);
          return false;
        } else if (role === '3') {
          this.router.navigate(['trainer']);
          return false;
        } else if (role === '4') {
          this.router.navigate(['user']);
          return false;
        }
        return true;
      });
    }
  }

  onSubmit() {
    console.log(this.form);

    this.signupInfo = new SignUpInfo(
      this.form.fname,
      this.form.lname,
      this.form.username,
      this.form.email,
      this.form.password);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.roles = this.tokenStorage.getAuthorities();
        this.roles.every(role => {
          if (role === '1') {
            this.router.navigate(['admin']);
            return false;
          } else if (role === '2') {
            this.router.navigate(['mng']);
            return false;
          } else if (role === '3') {
            this.router.navigate(['trainer']);
            return false;
          }else if (role === '4') {
            this.router.navigate(['user']);
            return false;
          }
          return true;
        });
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }
}
