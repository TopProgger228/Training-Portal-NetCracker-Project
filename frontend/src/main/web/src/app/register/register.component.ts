import { Component, OnInit } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';

import { TokenStorageService } from '../auth/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MailSenderService } from '../services/MailSender.service';
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
  token: string = '';

  roles: string[] = [];
  errorMessage = '';

  constructor(private authService: AuthService,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private route: ActivatedRoute,
    private mailSenderService: MailSenderService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isSignedUp = true;

      this.router.navigate(['auth/login']);
    } else {
      console.log(this.route.snapshot.paramMap.keys);
      this.route.queryParams.subscribe((params) => {
        //check lead Id here
        if (params['token']) {
          this.token = params['token'];
          console.log(this.token);
        } else {
          console.log('token not found in params');
          alert("this site is not available for you");
        }
      });
    }

  }

  onSubmit() {
    console.log(this.form);

    this.signupInfo = new SignUpInfo(
      this.form.fname,
      this.form.lname,
      this.form.username,
      this.form.password,
      this.token);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.roles = this.tokenStorage.getAuthorities();
        this.router.navigate(['auth/login']);
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
