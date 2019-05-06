import { Component, OnInit } from '@angular/core';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PasswordValidator } from './password-validation';
import { AuthService } from '../auth/auth.service';


import { TokenStorageService } from '../auth/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MailSenderService } from '../services/MailSender.service';
@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  passwordResetFormGroup: FormGroup;
  passwordFormGroup: FormGroup;

  token: string = '';
  email: string = 'email';

  constructor(
    private router: Router,
    private tokenStorage: TokenStorageService,
    private route: ActivatedRoute, 
    private formBuilder: FormBuilder, 
    private mailSenderService: MailSenderService,
    private authService: AuthService) {
    this.passwordFormGroup = this.formBuilder.group({
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required]
    }, {
      validator: PasswordValidator.validate.bind(this)
    });
    this.passwordResetFormGroup = this.formBuilder.group({
      passwordFormGroup: this.passwordFormGroup
    });
   }


  ngOnInit() {
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

    this.mailSenderService.getEmailByToken(this.token).subscribe(data=>{
      console.log(data);
      if(data.partialText){
        this.email = data.partialText;
      }
    },
    error=>{
      console.log(error);
    })

    console.log(this.email);
  }
  onSubmit() {
    
    console.log(this.email, this.passwordFormGroup.controls.password.value);
    this.authService.resetPassword(this.email, this.passwordFormGroup.controls.password.value).subscribe(
      data=>{
        console.log(data);
    },
    error=>{
      console.log(error);
    })
  }

}
