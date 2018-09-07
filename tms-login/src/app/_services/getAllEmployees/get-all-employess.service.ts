import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


import {EmployeesService} from 'src/app/_services/employees/employees.service';
import {AdminConfigComponent} from 'src/app/pages/adminConfig/admin-config.component';

const httpOptions = {

  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  
  };  

@Injectable({
  providedIn: 'root'
})

export class GetAllEmployessService {

private url: string;

constructor(private http:HttpClient) { 

this.url = "http://localhost:8101/employees";

}


public getEmployee() {

return this.http.get<EmployeesService[]>(this.url);

}

// public setSelectedGroup(group : Group)
// {
//   this.group= group;
// }

// public getSelectedGroup() {

//     return this.group;
// }

}
