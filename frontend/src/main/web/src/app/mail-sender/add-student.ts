import { Component, OnInit, Input } from '@angular/core';
import { MailSenderService } from '../services/MailSender.service';

import { ToasterService } from "../services/toaster.service";
import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-student-mail-sender',
  templateUrl: './add-student.html',
  styleUrls: ['./mail-sender.component.css']
})
export class AddStudentMailSenderComponent implements OnInit {

  form: any = {};

  sentMessage: string = null;
  nextPage: string = "Go to laning page -->";


  constructor(private mailSenderService: MailSenderService,
    private router: Router,
    private token: TokenStorageService,
    private toasterService: ToasterService) { }

  ngOnInit() {

    if (this.token.getToken()) {
      this.token.getAuthorities().every(role => {
        if (role === 'Admin') {
        } else {
          this.router.navigate(['firstPage']);
        }
        return false;
      });
    } else {
      this.router.navigate(['auth/login']);
    };

  }

  onSubmit() {
    this.mailSenderService.sendSignUpMail(this.form.email).subscribe(
      data => {
        console.log(data);

        if (data.body)
          this.toasterService.Success("Success", "Mail sent successfully");
      },
      error => {
        console.log(error);
        this.toasterService.Error("Error!", "Http failure response");
      }
    );
  }

}
