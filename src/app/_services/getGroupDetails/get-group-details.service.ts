import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component , OnInit} from '@angular/core';
import { GetRequestService } from 'src/app/_services/getRequest/get-request.service';
import { Group } from 'src/app/_services/group/group.service';
import { Observable, of } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class GetGroupDetailsService {

  httpOptions = {headers: new HttpHeaders({ 'Content-Type': 'application/json' })};
  title = 'app';
  groups: Group[];

  constructor(private http: HttpClient, private getRequest: GetRequestService) { }

  getGroup(): Group[] {
    this.getRequest.getGroup().subscribe((groups: Group[]) => (this.groups = groups));
    return this.groups;
    
  }

  ngOnInit() {
   
  }

}