import { Component, OnInit } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { SignInInfo } from '../auth/signin-info';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})

export class SignInComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: SignInInfo;

  constructor(private authService: AuthService,
    private router: Router, private tokenStorage: TokenStorageService) { }


  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
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

        this.isLoginFailed = false;
        this.isLoggedIn = true;
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
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }
}
