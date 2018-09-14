import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders,HttpParams } from '@angular/common/http';


import {Group} from 'src/app/_modal/group.service';


  const httpOptions = {

    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
   };


   @Injectable({
       providedIn: 'root'
    })

   export class GetGroupsService {

    private url: string;
    private urlforgroup: string; 
    group : Group;
    id;

    constructor(private http:HttpClient) { 

    this.url = "http://localhost:7090/group";

   }

    public getGroup ( page : number ) {

      return this.http.get<Group[]>(this.url,{

      headers: new HttpHeaders({
              
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'withCredentials': 'true'
      }),
              
      params : new HttpParams().set("page",page.toString()).set("total", '5')
  
    });

  }

   public setSelectedGroup(group : Group ) {
      this.group= group;
   }

   public getSelectedGroup() {
      return this.group;
   }

  public getCount(){

    return this.http.get<number>("http://localhost:7090/group/count");
  }
}
