import {Component, OnInit} from '@angular/core';
import {Student} from "../services/student";
import {Manager} from "../services/manager";
import {ActivatedRoute, Router} from "@angular/router";
import {StudentService} from "../services/student.service";
import {ManagerService} from "../services/manager.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-manager-profile',
  templateUrl: './manager-profile.component.html',
  styleUrls: ['./manager-profile.component.css']
})
export class ManagerProfileComponent implements OnInit {

  student: Student[];
  username: string;
  manager: Manager;
  hasPhoto = false;
  loggedOut = false;

  constructor(private route: ActivatedRoute, private router: Router, private studentService: StudentService, private managerService: ManagerService,
              private token: TokenStorageService, private sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    if (this.token.getToken()) {
      //this.token.getAuthorities().every(role => {
      //if (role === 'Manager') {
      this.route.params.subscribe(params => {
        this.username = params['username'];
      });

      console.log(this.username);
      this.managerService.getManagerOfStudent(this.username)
        .subscribe(data => {
          this.manager = data;
          if(data.photo != null) {
            this.manager.photo = this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + data.photo);
            this.hasPhoto=true;
          }
        });
      //} else {
      //  this.router.navigate(['firstPage']);
      // }
      return false;
      //});
    } else {
      this.loggedOut = true;
      this.router.navigate(['auth/login']);
    }
    ;
  };

  logout() {
    this.loggedOut = true;
    this.token.signOut();
    window.location.reload();
    this.router.navigate(['auth/login']);
  };

}
