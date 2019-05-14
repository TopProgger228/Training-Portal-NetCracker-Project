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


  nextPage: string = "Go to login page -->";
  sentMessage: string = null;
  isLoginFailed = false;
  errorMessage = '';

  constructor(private mailSenderService: MailSenderService,
    private router: Router,
    private token: TokenStorageService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      console.log(this.token.getToken());
      this.router.navigate(['firstPage']);
    }
  }


  onSubmit() {
    this.mailSenderService.sendResetPasswordMail(this.form.email).subscribe(
      data => {
        console.log(data);
        this.setSentMessage(data);
        this.isLoginFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }


  setSentMessage(data: any) {
    if (data.body) {
      if (data.statusText === "OK") this.sentMessage = "Mail sent successfully";
      else if (data.statusText === "BAD_REQUEST") this.sentMessage = "User does not exist"
      else this.sentMessage = "Something going wrong, try later";
    }
    else this.sentMessage = "Mail does not sent yet";
  }
}
