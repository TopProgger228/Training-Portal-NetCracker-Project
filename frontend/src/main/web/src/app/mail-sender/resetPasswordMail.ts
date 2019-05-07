import { Component, OnInit, Input } from '@angular/core';
import { MailSenderService } from '../services/MailSender.service';

import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-password-reset-mail-sender',
  templateUrl: './mail-sender.component.html',
  styleUrls: ['./mail-sender.component.css']
})
export class PasswordResetMailSenderComponent implements OnInit {

  form: any = {};
  loggedout = false;


  isLoginFailed = false;
  errorMessage = '';

  constructor(private mailSenderService: MailSenderService,
    private router: Router,
    private token: TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.router.navigate(['firstPage']);
    }
  }


  onSubmit() {
    this.mailSenderService.sendResetPasswordMail(this.form.email).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['auth/login']);

        this.isLoginFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

}
