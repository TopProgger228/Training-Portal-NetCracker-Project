export class SignUpInfo {
  fname: string;
  lname: string;
  username: string;
  role: string[];
  password: string;
  email: string;
  //photo: File;

  constructor(fname: string, lname: string, username: string, password: string, email: string/*, photo: File*/) {
    this.fname = fname;
    this.lname = lname;
    this.username = username;
    this.password = password;
    this.role = ['user'];
    this.email = email;
    //this.photo = photo;
  }
}
