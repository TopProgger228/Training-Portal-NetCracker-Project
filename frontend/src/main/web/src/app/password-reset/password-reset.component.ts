import { Component, OnInit } from '@angular/core';
import { MailSenderService } from '../services/MailSender.service';
import { UserModel } from '../services/user-model';
@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.css']
})
export class PasswordResetComponent implements OnInit {

  constructor(private mailSenderService: MailSenderService) { }

  id: number = 160;
  user: UserModel;
  ngOnInit() {
   // this.user = { 'fname': 'nastya', 'lname': 'artemenko', 'username': 'nino', 'email': 'artemenkonastya22@gmail.com', 'id': 160 };
    /*this.mailSenderService.sendResetPasswordMail(this.id).subscribe(
      data => {

        console.log(data);
      }
    );*/
  }

}


