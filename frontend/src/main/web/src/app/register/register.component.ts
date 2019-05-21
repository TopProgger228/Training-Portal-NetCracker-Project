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
  email: string = 'Loading...';

  roles: string[] = [];
  errorMessage = '';

  constructor(private authService: AuthService,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private mailSenderService: MailSenderService,
    private route: ActivatedRoute) { }
 
  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isSignedUp = true;

      this.router.navigate(['auth/login']);
    } else {
      console.log(this.route.snapshot.paramMap.keys);
      this.route.queryParams.subscribe((params) => {
        //check lead Id here
        if (params['token']) {
          console.log(params['token']);
          this.mailSenderService.getEmailByToken(params['token']).subscribe(
            data => {
              console.log(data);
              if (data.partialText) {
                this.email = data.partialText;
              }
            },
            error => {
              console.log(error);
            })
          console.log(this.email);
    
        } else {
          console.log('token not found in params');
          alert("this site is not available for you");
        }
      });
    }

  }

  onSubmit() {
    this.signupInfo = new SignUpInfo(
      this.form.fname,
      this.form.lname,
      this.form.username,
      this.form.password,
      this.email,
      //this.selectedFile
    );

    console.log(this.signupInfo);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.roles = this.tokenStorage.getAuthorities();
        this.router.navigate(['auth/login']);
        //this.authService.uploadPhoto(this.signupInfo.username, this.selectedFile);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );

  // if(this.ind) {
  //     this.authService.uploadPhoto(this.signupInfo.username, this.selectedFile).subscribe(
  //       data => {
  //         console.log(data);
  //         this.router.navigate(['auth/login']);
  //       },
  //       error => {
  //         console.log(error);
  //         this.router.navigate(['auth/login']);
  //       }
  //    );
   //}
  }

  // onFileChanged(event) {
  //   this.selectedFile = event.target.files.item(0);
  // }

  reloadPage() {
    window.location.reload();
  }
}
