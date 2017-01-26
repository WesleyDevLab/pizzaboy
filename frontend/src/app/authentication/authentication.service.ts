import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Subject } from 'rxjs/Subject';

import 'rxjs/add/operator/toPromise';

import { User } from './user.model';

@Injectable()
export class AuthenticationService {
  private jsonheaders = new Headers({'Content-Type': 'application/json'});
  private jsonoptions = new RequestOptions({headers: this.jsonheaders});
  private loggedIn = new Subject<string>();
  public loggedIn$ = this.loggedIn.asObservable();
  public username: string;

  constructor(private http: Http) { }

  public login(username: string, password: string) : Promise<User> {
    let p = new URLSearchParams();
    p.append('username', username);
    p.append('password', password);
    return this.http.post("http://localhost:8080/api/authentication", p, new RequestOptions({
      headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'})
    }))
      .toPromise().then(() => {
        this.username = username;
        this.loggedIn.next(username);
        let u = new User();
        u.mail = username;
        return u;
      }).catch(this.loginError);
  }

  public logout() {
    //this.http.post()
    this.loggedIn.next("");
  }

  public register(reguser: User) : Promise<User> {
    return this.http.post('http://localhost:8080/api/user', JSON.stringify(reguser), this.jsonoptions).toPromise().then(() => reguser).catch(this.registerError);
  }

  public isLoggedIn() : boolean {
    return this.username.length > 0;
  }

  private registerError(error: any) : Promise<any> {
    console.log("register error");
    return Promise.reject(error.message || error);
  }

  private loginError(error: any) : Promise<any> {
    console.log("login failed");
    return Promise.reject(error.message || error);
  }
}
