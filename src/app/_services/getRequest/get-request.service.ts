import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService} from 'src/app/_services/authentication.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class GetRequestService {
  

  // url = 'http://172.16.18.180:8101';

  constructor(private http: HttpClient,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,) {     
 }

  getGroup() {
    return this.http.get('http://172.16.18.180:8101/groups');

  }
}
