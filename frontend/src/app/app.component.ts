import { Component, OnDestroy } from '@angular/core';

import { AuthenticationService } from './authentication/authentication.service';

import { Subscription }   from 'rxjs/Subscription';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {
  username: string = "";
  private subscription: Subscription;
  
  constructor(private authService: AuthenticationService) {
    authService.loggedIn$.subscribe(username => {
      console.log(username);
      this.username = username;
    });
  }

  public logout() {
    this.authService.logout();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
