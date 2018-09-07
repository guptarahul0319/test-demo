import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


import {Group} from 'src/app/_services/group/group.service';


const httpOptions = {

headers: new HttpHeaders({ 'Content-Type': 'application/json'})

};


@Injectable({

providedIn: 'root'

})

export class GetRequestService {

private url: string;
private urlforgroup: string; 
group : Group;

constructor(private http:HttpClient) { 

this.url = "http://localhost:8101/groups";

}


public getGroup () {

return this.http.get<Group[]>(this.url);

}

public setSelectedGroup(group : Group)
{
  this.group= group;
}

public getSelectedGroup() {

    return this.group;
}

}
