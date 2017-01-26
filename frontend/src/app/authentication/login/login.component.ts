import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap';

import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  exportAs: 'child'
})
export class LoginComponent implements OnInit {
  email: string;
  pw: string;
  lerror: boolean;

  constructor(private authService: AuthenticationService) { }

  public login() {
    console.log(this.email + " " + this.pw);
    this.authService.login(this.email, this.pw).then(u => {
      console.log(u);
      this.pw = "";
      this.hideLogin();
    }).catch(() => this.lerror = true);
  }

  ngOnInit() {
  }

  @ViewChild('loginModal')
  public loginModal: ModalDirective;

  public showLogin() {
    this.loginModal.show();
  }

  public hideLogin() {
    this.loginModal.hide();
  }
}