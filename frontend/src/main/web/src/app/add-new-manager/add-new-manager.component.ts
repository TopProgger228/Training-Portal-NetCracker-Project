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

  onSubmit(){
    console.log(this.userModel);
    this.httpService.addMember(this.userModel).subscribe(
      value => {
        console.log('[POST] create user successfully', value);
      }, error => {
        console.log('FAIL to create user!');
      },
      () => {
        console.log('POST user - now completed.');
      });
  }
}

