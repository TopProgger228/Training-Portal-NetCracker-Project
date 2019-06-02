import { Component, OnInit } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';


import { ToasterService } from "../services/toaster.service";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UpperCaseValidator } from '../validators/upperCase-validation';
import { PasswordCompareValidator } from '../validators/password-compare-validation';

import { TokenStorageService } from '../auth/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MailSenderService } from '../services/MailSender.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerFormGroup: FormGroup;
  passwordFormGroup: FormGroup;

  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  email: string = 'email';
  ErrorMessage: string = 'Loading...';

  roles: string[] = [];

  constructor(private authService: AuthService,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private mailSenderService: MailSenderService,
    private formBuilder: FormBuilder,
    private toasterService: ToasterService,
    private route: ActivatedRoute) {

    this.passwordFormGroup = this.formBuilder.group({
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required]
    }, {
        validator: PasswordCompareValidator.validate.bind(this)
      });

    this.registerFormGroup = this.formBuilder.group({
      passwordFormGroup: this.passwordFormGroup,
      fname: ['', Validators.required],
      lname: ['', Validators.required],
      username: ['', Validators.required],
    },
      {
        validator: UpperCaseValidator.validate.bind(this)
      });
  }

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
                  if (data.partialText === "Time to live for url out")
                    this.ErrorMessage = data.partialText;
                  else if (data.partialText === "Current URL does not exist")
                    this.ErrorMessage = data.partialText;
                  else {
                    this.email = data.partialText;
                    this.ErrorMessage = '';
                  }
                }
              },
              error => {
                console.log(error);
              })
          console.log(this.email);

        } else {
          console.log('token not found in params');
        }
      });
    }

  }

  onSubmit() {
    this.signupInfo = new SignUpInfo(
      this.registerFormGroup.controls.fname.value,
      this.registerFormGroup.controls.lname.value,
      this.registerFormGroup.controls.username.value,
      this.passwordFormGroup.controls.password.value,
      this.email,
      //this.selectedFile
    );

    console.log(this.signupInfo);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        if (data.message === "registered successfully") {
          this.toasterService.Success("Success", "User created successfully");
          this.isSignedUp = true;
          this.isSignUpFailed = false;
          this.roles = this.tokenStorage.getAuthorities();
          this.router.navigate(['auth/login']);
        } else if (data.message === "username is already taken") {
          this.toasterService.Warning("Warning", "User with this username is already exist, try amother one");
          this.isSignUpFailed = true;
        } else if (data.message === "email is already taken") {
          this.toasterService.Warning("Warning", "User with this email is already taken, try to reset your password");
          this.isSignUpFailed = true;
        }//this.authService.uploadPhoto(this.signupInfo.username, this.selectedFile);
      },
      error => {
        console.log(error);
        this.isSignUpFailed = true;
        this.toasterService.Error("Error", "Something wrong, try later");
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }
}
