import { Component, OnInit } from '@angular/core';
import { MailSenderService } from '../services/MailSender.service';

import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent implements OnInit {

  form: any = {};
  loggedout = false;

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
