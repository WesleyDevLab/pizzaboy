import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { LoginComponent } from './authentication/login/login.component'
import { AuthenticationService } from './authentication/authentication.service';

import { Subscription }   from 'rxjs/Subscription';
import { MenuItem } from 'primeng/primeng';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  username: string = "";
  private subscription: Subscription;
  private items: MenuItem[];

  @ViewChild('loginDialog')
  private loginCompo: LoginComponent;
  
  constructor(private authService: AuthenticationService) {
  }

  public logout() {
    this.authService.logout();
  }

  public showLogin() {
    this.loginCompo.showLogin();
  }

  ngOnInit() {
    this.authService.loggedIn$.subscribe(username => {
      console.log(username);
      this.username = username;
    });

    this.setMenu();
  }

  public setMenu() {
    this.items = [{
      label: 'pizza',
      routerLink: ['/pizza']
    }];

    if(this.adminLoggedIn()) {
      this.items[1] = {
        label: this.username,
        items: [{
          label: 'current orders',
          routerLink: ['/admin/orders']
        },
        {
          label: 'edit pizzas',
          routerLink: ['/admin/pizzas']
        },
        {
          label: 'edit ingredients',
          routerLink: ['/admin/ingredients']
        }]
      };
    }
    
    if(this.loggedIn() && !this.adminLoggedIn()) {
      this.items[1] = {
        label: this.username,
        items: [{
          label: 'orders',
          routerLink: ['/user/orders']
        },
        {
          label: 'customer',
          routerLink: ['/user/customer']
        }]
      }
    }
    
    if(!this.loggedIn()) {
      this.items[1] = {
        label: 'login',
        command: (event) => {
          this.loginCompo.showLogin();
        }
      };
    }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public loggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  public adminLoggedIn(): boolean {
    return this.authService.isAdmin();
  }
}
