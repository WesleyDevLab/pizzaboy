import { Component, ViewChild } from '@angular/core';

import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  exportAs: 'child'
})
export class LoginComponent {
  public email: string;
  public pw: string;
  public lerror: boolean;
  public show: boolean = false;

  constructor(private authService: AuthenticationService) { }

  public login() {
    console.log(this.email + " " + this.pw);
    this.authService.login(this.email, this.pw).then(() => {
      this.hideLogin();
    }).catch(() => this.lerror = true);
  }

  public showLogin() {
    this.show = true;
  }

  public hideLogin() {
    this.show = false;
    this.email = "";
    this.pw = "";
    this.lerror = false;
  }
}
