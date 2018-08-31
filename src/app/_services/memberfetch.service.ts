import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class MemberfetchService {

  constructor(private http: HttpClient) { }




  getMemberDetails() {
    // return this.http.get()
    return '';
  }
}
