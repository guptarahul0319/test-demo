import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable, empty } from 'rxjs/';
import { catchError } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';



@Injectable()
export class AuthenticationService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'withCredentials': 'true'
    })
  };

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post('http://172.16.17.170:7090/login', { "userName": username, "password": password }, this.httpOptions).pipe(
      map(response => {
        if (response) {
          return response;
        }
      }),
      catchError((err, caught) => {
        return Observable.empty();
      }));
  }



}


