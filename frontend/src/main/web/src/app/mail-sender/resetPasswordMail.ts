import { Component, OnInit, Input } from '@angular/core';
import { MailSenderService } from '../services/MailSender.service';

import { ToasterService } from "../services/toaster.service";
import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-password-reset-mail-sender',
  templateUrl: './resetPassword.html',
  styleUrls: ['./mail-sender.component.css']
})
export class PasswordResetMailSenderComponent implements OnInit {

  form: any = {};
  loggedout = false;


  nextPage: string = "Go to login page -->";
  isLoginFailed = false;
  errorMessage = '';

  constructor(private mailSenderService: MailSenderService,
    private router: Router,
    private token: TokenStorageService,
    private toasterService: ToasterService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.router.navigate(['firstPage']);
    }
  }


  onSubmit() {
    this.mailSenderService.sendResetPasswordMail(this.form.email).subscribe(
      data => {
        console.log(data);

        if (data.partialText) {

          console.log(data.partialText);
          if (data.partialText === '{"message":"sent"}') this.toasterService.Success("Success", "Mail sent successfully");
          else if (data.partialText === '{"message":"not exist"}') this.toasterService.Warning("Warning", "User does not exist");
        }
        this.isLoginFailed = false;
      },
      error => {
        console.log(error);
        this.toasterService.Error("Error!", "Http failure response");
        this.isLoginFailed = true;
      }
    );
  }
}
