import { Component, OnInit, OnDestroy } from '@angular/core';

import { AuthenticationService } from './authentication/authentication.service';

import { Subscription }   from 'rxjs/Subscription';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  username: string = "";
  private subscription: Subscription;
  
  constructor(private authService: AuthenticationService) {
  }

  public logout() {
    this.authService.logout();
  }

  ngOnInit() {
    this.authService.loggedIn$.subscribe(username => {
      console.log(username);
      this.username = username;
    });
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
