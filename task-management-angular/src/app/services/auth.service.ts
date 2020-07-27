import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  baseURL: string = "https://taskey-edge-service.herokuapp.com/api/";
  tokenType: string;
  accessToken: string;
  email: string;
  userID: number;
  userRole: string;
  isLogged: boolean = false;

  constructor(private httpClient: HttpClient, private router: Router) {}

  public setUser(user) {
    console.log(user, "USER");
    this.tokenType = user.tokenType;
    this.accessToken = user.accessToken;
    this.email = user.email;
    this.userRole = user.userRole;
    this.userID = user.userID;
  }

  public getJWToken() {
    return `${this.tokenType} ${this.accessToken}`;
  }

  public getIsLogged() {
    return this.isLogged;
  }

  public getUserID(): number {
    console.log(this.userID);
    return this.userID;
  }

  public login(data) {
    return new Promise((resolve, reject) => {
      this.httpClient.post(`${this.baseURL}auth/sign-in`, data).subscribe(
        (postedData) => {
          this.setUser(postedData);
          this.isLogged = true;
          resolve(postedData);
        },
        (error) => {
          reject(error.error.message);
        }
      );
    });
  }

  public logout() {
    this.tokenType = "";
    this.accessToken = "";
    this.isLogged = false;
    this.router.navigate([""]);
  }
}
