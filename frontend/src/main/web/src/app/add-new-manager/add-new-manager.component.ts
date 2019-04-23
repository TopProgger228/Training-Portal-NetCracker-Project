import { Component} from '@angular/core';
import {User} from "../services/user";
import {AddMemberService} from "../services/add-member.service";

@Component({
  selector: 'app-add-new-manager',
  templateUrl: './add-new-manager.component.html',
  styleUrls: ['./add-new-manager.component.css']
})
export class AddNewManagerComponent{
  roles = ['Trainer', 'Manager'];

  userModel = new User('', '', this.roles[0], '',
    '', '');

  constructor(private httpService : AddMemberService){

  }

  onSubmit(user : User){
    this.httpService.addMember(user).subscribe(
      (data)=>console.log(data)
    );
  }
}

