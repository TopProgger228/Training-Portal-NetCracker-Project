export class SignUpInfo {
  fname: string;
  lname: string;
  username: string;
  role: string[];
  password: string;
  token: string

  constructor(fname: string, lname: string, username: string, password: string, token: string) {
    this.fname = fname;
    this.lname = lname;
    this.username = username;
    this.password = password;
    this.role = ['user'];
    this.token = token;
  }
}
