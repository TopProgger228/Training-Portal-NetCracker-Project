import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-mng',
  templateUrl: './mng.component.html',
  styleUrls: ['./mng.component.css']
})
export class MngComponent implements OnInit {
  board: string;
  errorMessage: string;
 
  constructor(private userService: UserService) { }
 
  ngOnInit() {
    this.userService.getMngBoard().subscribe(
      data => {
        this.board = data;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }
}
