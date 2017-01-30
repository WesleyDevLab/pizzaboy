import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { AuthHttp, tokenNotExpired, JwtHelper } from 'angular2-jwt';

import 'rxjs/add/operator/toPromise';

import { User } from './user.model';

@Injectable()
export class AuthenticationService {
  private jsonheaders = new Headers({'Content-Type': 'application/json'});
  private jsonoptions = new RequestOptions({headers: this.jsonheaders});
  private loggedIn = new BehaviorSubject<string>("");
  public loggedIn$ = this.loggedIn.asObservable();
  public username: string;
  jwtHelper: JwtHelper = new JwtHelper();

  constructor(private ahttp: AuthHttp) {
    console.log("ctr");
    if(localStorage.getItem("id_token") != null) {
      this.setUsername();
    }
  }

  public login(username: string, password: string) : Promise<void> {
    console.log(JSON.stringify({username, password}));
    return this.ahttp.post("http://localhost:8080/api/login", JSON.stringify({username, password}), this.jsonoptions)
      .toPromise().then((u) => {
        console.log(u);
        u.headers.forEach(element => {
          console.log(element);
        });
        let t = u.headers.get("authorization");
        t = t.replace("Bearer ", "");
        localStorage.setItem('id_token', t);
        this.setUsername();
      }).catch(this.loginError);
  }

  private setUsername() {
    let t = localStorage.getItem("id_token");
    this.loggedIn.next(this.jwtHelper.decodeToken(t).sub);
  }

  public logout() {
    localStorage.removeItem('id_token');
    this.loggedIn.next("");
  }

  public register(reguser: User) : Promise<User> {
    return this.ahttp.post('http://localhost:8080/api/user', JSON.stringify(reguser), this.jsonoptions).toPromise().then(() => reguser).catch(this.registerError);
  }

  public isLoggedIn(): boolean {
    return tokenNotExpired();
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
