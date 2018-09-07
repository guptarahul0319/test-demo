import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class Group {

  serialNumber: number;
  adminId : number;
  adminName: string;
  groupEmpIds: number[];
  groupName: string;
  description: string;
  isArchived: string;


  constructor(groupEmpIds: number[]){
    this.groupEmpIds= groupEmpIds;
}
}
