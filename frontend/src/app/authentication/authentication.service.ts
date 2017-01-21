import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { User } from './user.model';

@Injectable()
export class AuthenticationService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private options = new RequestOptions({headers: this.headers});

  constructor(private http: Http) { }

  public login(username: string, password: string) {
    this.http.post("http://localhost:8080/api/authentication", JSON.stringify({ 'username': username, 'password': password }), this.options).subscribe();
  }

  public register(reguser: User) : Promise<User> {
    return this.http.post('http://localhost:8080/api/user', JSON.stringify(reguser), this.options).toPromise().then(() => reguser).catch(this.registerError);
  }

  private registerError(error: any) : Promise<any> {
    console.log("register error");
    return Promise.reject(error.message || error);
  }
}
