import { Component} from '@angular/core';
import {User} from "../services/user";

@Component({
  selector: 'app-add-new-manager',
  templateUrl: './add-new-manager.component.html',
  styleUrls: ['./add-new-manager.component.css']
})
export class AddNewManagerComponent{
  roles = ['Trainer', 'Manager'];

  userModel = new User('Dima', 'Pylypiuk', this.roles[0], 'user1',
    'user@gmail.com', 'userpass');

  submitted = false;

  onSubmit(){
    this.submitted = true;
  }
}

