// import { Component, OnInit } from '@angular/core';
// import { Router, ActivatedRoute } from '@angular/router';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { AuthenticationService} from 'src/app/_services/authentication.service';
// import { Injectable } from '@angular/core';
// import {Group} from 'src/app/_services/group/group.service';
// import { Observable, of } from 'rxjs';
// import {catchError } from 'rxjs/operators';
// import * as _ from 'lodash';
// import { HttpClient, HttpHeaders } from '@angular/common/http';



import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import {Group} from 'src/app/_services/group/group.service';
import {AdminConfigComponent} from 'src/app/pages/adminConfig/admin-config.component';


const httpOptions = {

headers: new HttpHeaders({ 'Content-Type': 'application/json'})

};


@Injectable({

providedIn: 'root'

})

export class GetRequestService {

private url: string; 
private adminConfigComponent: AdminConfigComponent;

constructor(private http:HttpClient) { 

this.url = "http://172.16.18.180:8101/groups";

}


public getGroup () {

return this.http.get<Group[]>(this.url);

}

public getSelectedGroup(serialNumber: number): Group {
  // TODO: send the message _after_ fetching the hero
  return ((this.adminConfigComponent.groups).find(groups => groups.serialNumber === serialNumber));
}

}
