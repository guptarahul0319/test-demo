import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {

  empName: string;
  empId: number; 
  emailId: string;
  isArchived: string;
}

