import { Component, OnInit, Input } from '@angular/core';
import { MailSenderService } from '../services/MailSender.service';

import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-student-mail-sender',
  templateUrl: './mail-sender.component.html',
  styleUrls: ['./mail-sender.component.css']
})
export class AddStudentMailSenderComponent implements OnInit {

  form: any = {};
  loggedout = false;
  
  @Input()
  resetPassword: boolean;

  constructor(private mailSenderService: MailSenderService,
    private router: Router,
    private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (!this.tokenStorage.getToken()) {
      this.loggedout = true;
      this.router.navigate(['auth/login']);
    };
  }

  logout() {
    this.loggedout = true;
    this.tokenStorage.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  }

  onSubmit(){
    if(this.resetPassword){

    }else{
      this.mailSenderService.sendSignUpMail(this.form.email).subscribe(
        data => {
          console.log(data);
          this.router.navigate(['firstPage']);
        },
        error => {
          console.log(error);
        }
      );
    }
  }

}
