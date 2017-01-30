import { Component, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap';

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

  @ViewChild('loginModal')
  private loginModal: ModalDirective;

  constructor(private authService: AuthenticationService) { }

  public login() {
    console.log(this.email + " " + this.pw);
    this.authService.login(this.email, this.pw).then(() => {
      this.hideLogin();
    }).catch(() => this.lerror = true);
  }

  public showLogin() {
    this.loginModal.show();
  }

  public hideLogin() {
    this.loginModal.hide();
    this.email = "";
    this.pw = "";
    this.lerror = false;
  }
}
