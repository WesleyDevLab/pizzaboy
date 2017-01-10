import { Injectable } from '@angular/core';

import { Http } from '@angular/http';

@Injectable()
export class AuthenticationService {

  constructor(private http: Http) { }

  public login(username: string, password: string) {
    this.http.post("http://localhost:8080/api/authentication", { 'username': username, 'password': password }).subscribe();
  }
}
