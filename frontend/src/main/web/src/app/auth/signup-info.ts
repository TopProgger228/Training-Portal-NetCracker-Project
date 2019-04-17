export class SignUpInfo {
  fname: string;
  lname: string;
    username: string;
    email: string;
    role: string[];
    password: string;

    constructor( fname: string, lname: string, username: string, email: string, password: string) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = ['user'];
    }
}
