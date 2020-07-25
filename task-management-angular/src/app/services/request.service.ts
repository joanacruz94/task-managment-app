import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class RequestService implements OnInit {
  baseURL: string = 'http://localhost:8080/api/';
  headers: object;

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) {}

  ngOnInit() {}

  setAuthHeader(): any {
    return new HttpHeaders().set(
      'Authorization',
      this.authService.getJWToken()
    );
  }

  public get(url) {
    const headers = this.setAuthHeader();
    return new Promise((resolve, reject) => {
      this.httpClient.get(`${this.baseURL}${url}`, { headers }).subscribe(
        (data) => {
          resolve(data);
        },
        (error) => {
          reject(error);
        }
      );
    });
  }

  public post(url, data): any {
    const headers = this.setAuthHeader();
    return new Promise((resolve, reject) => {
      this.httpClient
        .post(`${this.baseURL}${url}`, data, { headers })
        .subscribe(
          (postedData) => {
            resolve(postedData);
          },
          (error) => {
            reject(error);
          }
        );
    });
  }

  public put(url, data): any {
    const headers = this.setAuthHeader();
    return new Promise((resolve, reject) => {
      this.httpClient.put(`${this.baseURL}${url}`, data, { headers }).subscribe(
        (updatedData) => {
          resolve(updatedData);
        },
        (error) => {
          reject(error);
        }
      );
    });
  }

  public delete(url) {
    const headers = this.setAuthHeader();
    return new Promise((resolve, reject) => {
      this.httpClient.delete(`${this.baseURL}${url}`, { headers }).subscribe(
        (data) => {
          resolve(data);
        },
        (error) => {
          reject(error);
        }
      );
    });
  }

  public patch(url, data) {
    const headers = this.setAuthHeader();
    return new Promise((resolve, reject) => {
      this.httpClient
        .patch(`${this.baseURL}${url}`, data, { headers })
        .subscribe(
          (data) => {
            resolve(data);
          },
          (error) => {
            reject(error);
          }
        );
    });
  }
}
