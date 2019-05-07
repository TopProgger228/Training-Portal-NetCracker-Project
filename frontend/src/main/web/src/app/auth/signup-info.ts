export class SignUpInfo {
  fname: string;
  lname: string;
  username: string;
  role: string[];
  password: string;
  email: string

  constructor(fname: string, lname: string, username: string, password: string, email: string) {
    this.fname = fname;
    this.lname = lname;
    this.username = username;
    this.password = password;
    this.role = ['user'];
    this.email = email;
  }
}
